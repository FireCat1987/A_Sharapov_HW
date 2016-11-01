import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.*;
import java.util.*;

class Server {

    private final List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server = null;
    private InetAddress serverAddress = null;

    Server() {
        Runnable broadcast = () -> {
            byte data[] = new byte[0];
            data = String.valueOf(connections.size()).getBytes();
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
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 9090);
            while (true) {
                try {
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
                server = new ServerSocket(6666);
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

    }

    private void closeAll() {
        try {
            if (server != null) {
                System.out.println("Останавливаем сервер");
                server.close();
                System.out.println("Отключаем клиентов");
                synchronized (connections) {
                    for (Connection connection : connections) {
                        (connection).close();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода при остановке сервера!");
            System.exit(0);
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
                close();
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();
                id = random.nextInt(100) + 1;
                out.println("Ваш id:" + id);
                synchronized (connections) {
                    for (Connection connection : connections) {
                        if(connection.getYourId() != id) (connection).out.println("[" + id + "] " + name + " вошёл в чат");
                    }
                }
                String str;
                while (true) {
                    str = in.readLine();
                    if (str.equals("exit")) break;
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
                synchronized (connections) {
                    for (Connection connection : connections) {
                        if(connection.getYourId() != id) (connection).out.println("[" + id + "] " + name + " вышел из чата");

                    }
                }
            } catch (IOException e) {
                System.err.println("Произошла непредвиденная ошибка. Подключение " + "[" + id + "] " + name + " будет закрыто.");
                close();
            }
        }

        int getYourId() {
            return id;
        }

        public String getYourName() {
            return name;
        }

        void close() {
            try {
                in.close();
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
