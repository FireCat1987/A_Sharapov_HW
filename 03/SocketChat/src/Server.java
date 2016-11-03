import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;


class Server {

    private final List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server = null;
    private InetAddress serverAddress = null;

    Server() {
        Runnable broadcast = () -> {

            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket();
                socket.setBroadcast(true);
            } catch (SocketException e) {
                System.err.println("Произошла ошибка создания сокета рассылки широковещательных запросов!");
                closeAll();
            }
            try {
                String adr = null;
                adr = InetAddress.getLocalHost().getHostAddress();
                StringBuilder sb = new StringBuilder();
                Scanner scaner = new Scanner(adr);
                scaner.useDelimiter("\\.");
                sb.append(scaner.next()).append(".");
                sb.append(scaner.next()).append(".");
                sb.append(scaner.next()).append(".");
                sb.append(255);
                serverAddress = InetAddress.getByName(sb.toString());
            } catch (UnknownHostException e) {
                System.err.println("Произошла ошибка получения адреса сервера!");
                closeAll();
            }
            byte data[] = new byte[0];
            while (true) {
                try {
                    data = String.valueOf(connections.size()).getBytes();
                    DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 9090);
                    assert socket != null;
                    socket.send(packet);
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    System.err.println("Произошла ошибка ввода/вывода в момент рассылки широковещательных сообщений!");
                    closeAll();
                }
            }
        };
        Runnable accepter = () -> {
            try {
                if (!portAvailable(6666)) System.out.println("В сети уже присутствует сервер с данным портом!");
                else System.out.println("Порты свободны!");
                SocketAddress sa = new InetSocketAddress(6666);
                ServerSocket server = new ServerSocket();
                server.setReuseAddress(true);
                server.bind(sa);
                //server = new ServerSocket(6666);
                System.out.println("Сервер успешно запущен! Ожидаем подключения клиентов...");
                while (true) {
                    Socket socket = server.accept();
                    Connection con = new Connection(socket);
                    connections.add(con);
                    con.start();
                    System.out.println("Клиент с ip " + socket.getInetAddress().getHostAddress() + " подключился!");
                    System.out.println("Всего клиентов " + connections.size());
                }

            } catch (IOException e) {
                System.err.println("Не удалось создать сокет на данном порту, либо порт уже занят другим сервером, либо неполадки в сети.!");
                System.exit(0);
            } finally {
                closeAll();
            }
        };

        new Thread(broadcast).start();
        new Thread(accepter).start();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String answer = sc.nextLine();
            switch (answer.toLowerCase()) {
                case "//command":
                    System.out.println("//list - список всех участников, //exit - выход из приложения, //exall - отключить всех пользователей");
                    break;
                case "//exall":
                    closeAll();
                    break;
                case "//list":
                    if (connections.size() > 0) {
                        synchronized (connections) {
                            System.out.println("Список участников:");
                            for (Connection i : connections) {
                                System.out.println("[" + i.id + "] " + i.name);
                            }
                        }
                    } else {
                        System.out.println("Список участников пуст");
                    }
                    break;
                case "//exit":
                    System.out.println("Выход из приложения");
                    try {
                        server.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка закрытия сервера");
                    }
                    System.exit(0);
                default:
                    System.out.println("Неизвестная комманда! Для вывода списка всех комманд введите //command.");
                    break;
            }
        }
    }

    private void closeAll() {
        System.out.println("Отключаем клиентов");
        synchronized (connections) {
            for (Connection i : connections) {
                (i).close((i).getYourId());
            }
        }

    }

    private boolean portAvailable(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;
        private String name = "";
        private int id;
        final Random random = new Random();

        Connection(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.err.println("Произошла ошибка при подключении.");
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();
                id = random.nextInt(100) + 1;
                out.println("Ваш id:" + id);
                synchronized (connections) {
                    connections.stream().filter(i -> i.getYourId() != id).forEach(i -> (i).out.println("[" + id + "] " + name + " вошёл в чат"));
                }
                String str;
                while (true) {
                    str = in.readLine();
                    if (str.equals("//exit")) break;
                    if (str.equals("//list")) {
                        synchronized (connections) {
                            out.println("Список участников:");
                            for (Connection i : connections) {
                                if (id != i.getYourId()) {
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
                    synchronized (connections) {
                        for (Connection i : connections) {
                            if (sid != 0) {
                                if (sid == i.getYourId()) {
                                    System.out.println("sid= " + sid + " id= " + i.getYourId());
                                    i.out.println("[" + id + "] " + name + ": " + str);
                                }
                            } else {
                                i.out.println("[" + id + "] " + name + ": " + str);
                            }
                        }
                    }
                }

                close(id);

            } catch (IOException e) {
                System.err.println("Произошла непредвиденная ошибка. Подключение " + "[" + id + "] " + name + " будет закрыто.");
                close(id);
            }
        }

        int getYourId() {
            return id;
        }

        void close(int id) {
            System.out.println("0");
            synchronized (connections) {
                for (Connection connection : connections) {
                    if (connection.getYourId() != id) {
                        (connection).out.println("[" + id + "] " + name + " вышел из чата");
                    }
                }
            }
            try {
                System.out.println("1");
                in.close();
                System.out.println("2");
                out.close();
                System.out.println("Клиент с ip " + socket.getInetAddress().getHostAddress() + " отключился!");
                socket.close();
                connections.remove(this);
                System.out.println("Осталось клиентов " + connections.size());
            } catch (IOException e) {
                System.err.println("При закрытии подключения " + "[" + id + "] " + name + " произошла ошибка!");
            }
        }
    }
}
