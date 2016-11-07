import java.io.*;
import java.net.*;
import java.util.*;

class Server {
    private final List<UserThread> userThreads =
            Collections.synchronizedList(new ArrayList<UserThread>());
    private final List<Message> messageHistory =
            Collections.synchronizedList(new ArrayList<Message>());
    private DatagramSocket datagramSocket = null;
    private DatagramPacket datagramPacket = null;
    private InetAddress inetAddress = null;
    private ServerSocket serverSocket = null;

    Server() {
        try {
            SocketAddress serverAddress = new InetSocketAddress(Const.SERVER_PORT);
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(serverAddress);
            System.out.println("Сервер успешно запущен! Ожидаем подключения клиентов...");
            datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(hostAddress);
            scanner.useDelimiter("\\.");
            stringBuilder.append(scanner.next()).append(".");
            stringBuilder.append(scanner.next()).append(".");
            stringBuilder.append(scanner.next()).append(".");
            stringBuilder.append(255);
            inetAddress = InetAddress.getByName(stringBuilder.toString());
        } catch (IOException e) {
            System.err.println("Произошла ошибка создания сокета рассылки широковещательных запросов!");
            e.printStackTrace();
        }
        Runnable broadcast = () -> {
            while (true) {
                try {
                    byte data[] = new byte[0];
                    data = String.valueOf(userThreads.size()).getBytes();
                    datagramPacket = new DatagramPacket(data, data.length, inetAddress, Const.BROADCAST_PORT);
                    assert datagramSocket != null;
                    datagramSocket.send(datagramPacket);
                    serverSocket.setSoTimeout(1000);
                    Socket acceptSocket = serverSocket.accept();
                    UserThread userThread = new UserThread(acceptSocket);
                    userThreads.add(userThread);
                    userThread.start();
                    System.out.println("Клиент с ip " + acceptSocket.getInetAddress().getHostAddress() + " подключился!");
                    System.out.println("Всего клиентов " + userThreads.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Произошла ошибка ввода/вывода в момент рассылки широковещательных сообщений!");
                    e.printStackTrace();
                    closeAll();
                } catch (SocketTimeoutException e) {
                    continue;
                } catch (IOException e) {
                    System.err.println("Произошла ошибка ввода/вывода в момент рассылки широковещательных сообщений!");
                    e.printStackTrace();
                    closeAll();
                }
            }
        };
        new Thread(broadcast).start();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command.toLowerCase()) {
                case "//command":
                    System.out.println("//list - список всех участников, //exit - выход из приложения, //exall - отключить всех пользователей");
                    break;
                case "//exall":
                    closeAll();
                    break;
                case "//list":
                    if (userThreads.size() > 0) {
                        synchronized (userThreads) {
                            System.out.println("Список участников:");
                            for (UserThread i : userThreads) {
                                System.out.println("[" + i.id + "] " + i.name);
                            }
                        }
                    } else {
                        System.out.println("Список участников пуст");
                    }
                    break;
                case "//exit":
                    System.out.println("Выход из приложения");
                    System.exit(0);
                default:
                    System.out.println("Неизвестная комманда! Для вывода списка всех комманд введите //command.");
                    break;
            }
        }
    }

    private void closeAll() {
        try {
            serverSocket.close();
            System.out.println("Отключаем клиентов");
            synchronized (userThreads) {
                for (UserThread i : userThreads) {
                    (i).close();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка остановка сервера и клиентов.");
            e.printStackTrace();
        }
    }

    private class UserThread extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        private Socket socket;
        private String name = "";
        private int id;
        private Message message;
        final Random random = new Random();

        UserThread(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                ois  = new ObjectInputStream(this.socket.getInputStream());
                oos = new ObjectOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Произошла ошибка при подключении.");
            }

        }

        @Override
        public void run() {
            try {
                this.message = (Message) ois.readObject();
                this.name = this.message.getName();
                this.id = random.nextInt(100) + 1;

                if (! this.message.getMessage().equals(Const.HELLO_MESSAGE)) {
                    System.out.println("[" + this.message.getId() + "] " + this.message.getName() + " : " + this.message.getMessage());
                    messageHistory.add(this.message);
                } else {
                    oos.writeObject(messageHistory);
                    synchronized (userThreads) {
                        userThreads.stream().filter(i -> i.getUserId() != id).forEach(i -> {
                            try {
                                (i).oos.writeObject(new Message("Server-Bot", "[" + id + "] " + name + " вошёл в чат", this.id));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
                String str;
                while (true) {
                    str = in.readLine();
                    if (str.equals("//exit")) break;
                    if (str.equals("//list")) {
                        synchronized (userThreads) {
                            out.println("Список участников:");
                            for (UserThread i : userThreads) {
                                if (id != i.getUserId()) {
                                    out.println("[" + i.id + "] " + i.name);
                                }
                            }
                        }
                        continue;
                    }
                    int sid = 0;
                    if (str.indexOf("[") == 0 && str.indexOf("]") > 0) {
                        sid = Integer.parseInt(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
                        str = str.replace("[" + sid + "]", "[приват]");
                    }
                    synchronized (userThreads) {
                        for (UserThread i : userThreads) {
                            if (sid != 0) {
                                if (sid == i.getUserId()) {
                                    System.out.println("sid= " + sid + " id= " + i.getUserId());
                                    i.out.println("[" + id + "] " + name + ": " + str);
                                }
                            } else {
                                i.out.println("[" + id + "] " + name + ": " + str);
                            }
                        }
                    }
                }

                close();

            } catch (IOException e) {
                System.err.println("Произошла непредвиденная ошибка. Подключение " + "[" + id + "] " + name + " будет закрыто.");
                close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        int getUserId() {
            return this.id;
        }

        String getUserName() {
            return this.name;
        }

        void close() {
            synchronized (userThreads) {
                for (UserThread i : userThreads) {
                    if (i.getUserId() != this.getUserId()) {
                        (i).out.println("[" + this.getUserId() + "] " + this.getUserName() + " вышел из чата");
                    }
                }
            }
            try {
                in.close();
                out.close();
                System.out.println("Клиент с ip " + socket.getInetAddress().getHostAddress() + " отключился!");
                socket.close();
                userThreads.remove(this);
                System.out.println("Осталось клиентов " + userThreads.size());
            } catch (IOException e) {
                System.err.println("При закрытии подключения " + "[" + id + "] " + name + " произошла ошибка!");
            }
        }
    }
}
