import java.io.*;
import java.net.*;
import java.util.*;

class Server {

    private final List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;

    Server() {
        try {

            DatagramSocket ds = new DatagramSocket();
            String adr = InetAddress.getLocalHost().getHostAddress();
            StringBuilder sb = new StringBuilder();
            Scanner scaner = new Scanner(adr);
            scaner.useDelimiter("\\.");
            sb.append(scaner.next()).append(".");
            sb.append(scaner.next()).append(".");
            sb.append(scaner.next()).append(".");
            sb.append(255);
            System.out.println(sb.toString());
            byte[] buffer;
            buffer = "Hello".getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(sb.toString()), 9090);
            ds.send(packet);

            server = new ServerSocket(6666);
            while (true) {
                Socket socket = server.accept();
                Connection con = new Connection(socket);
                connections.add(con);
                con.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            server.close();
            synchronized (connections) {
                for (Connection connection : connections) {
                    (connection).close();
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка!");
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
                e.printStackTrace();
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
                        (connection).out.println("[" + id + "] " + name + " вошёл в чат");
                    }
                }
                String str = "";
                while (true) {
                    str = in.readLine();
                    if (str.equals("exit")) break;
                    int sid = 0;
                    if (str.indexOf("[") == 0 && str.indexOf("]") > 0) {
                        sid = Integer.parseInt(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
                        str.replace("[" + sid + "]", "[приват]");
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
                        (connection).out.println("[" + id + "] " + name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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
                socket.close();
                connections.remove(this);
                if (connections.size() == 0) {
                    Server.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Ошибка!");
            }
        }
    }
}

class Broadcasts {
    private final Runnable receiver;
    private final Runnable sender;
    private boolean run = true;
    public Broadcasts(Main parent) {
        receiver = new Runnable() {
            public void run() {
                byte data[] = new byte[0];
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(9999);
                } catch (SocketException ex) {
                    ex.printStackTrace();
                    parent.quit();
                }
                DatagramPacket packet = new DatagramPacket(data, data.length);
                while (run) {
                    try {
                        socket.receive(packet);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    }
                    //parent.newAddress(packet.getAddress());
                }
            }
        };
        sender = new Runnable() {
            public void run() {
                byte data[] = new byte[0];
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket();
                } catch (SocketException ex) {
                    ex.printStackTrace();
                    parent.quit();
                }
                String adr = null;
                try {
                    adr = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                StringBuilder sb = new StringBuilder();
                Scanner scaner = new Scanner(adr);
                scaner.useDelimiter("\\.");
                sb.append(scaner.next()).append(".");
                sb.append(scaner.next()).append(".");
                sb.append(scaner.next()).append(".");
                sb.append(255);
                DatagramPacket packet = null;
                try {
                    packet = new DatagramPacket(
                            data,
                            data.length,
                            InetAddress.getByName(sb.toString()),
                            9999);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                while (run) {
                    try {
                        socket.send(packet);
                        Thread.sleep(10);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    }
                }
            }
        };
        new Thread(receiver).start();
        new Thread(sender).start();
    }

    public void quit() {
        run = false;
    }

}