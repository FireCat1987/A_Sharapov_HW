import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {


    private List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;


    public Server() {
        try {
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
                Iterator<Connection> iter = connections.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).close();
                }
            }
        } catch (Exception e) {
            System.err.println("Fehler!");
        }
    }

    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        private String name = "";
        private int id;
        final Random random = new Random();

        public Connection(Socket socket) {
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
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).out.println("[" + id + "] " + name + " cames now");
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if (str.equals("exit")) break;

                    int sid = 0;
                    if (str.indexOf("[") == 0 && str.indexOf("]") > 0) {
                        sid = Integer.parseInt(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
                        str.replace("[" + sid + "]", "[private]");
                    }

                    synchronized (connections) {

                        Iterator<Connection> iter = connections.iterator();
                        while (iter.hasNext()) {
                            Connection i = iter.next();
                            if (sid != 0) {

                                if (sid == i.getYourId()) {
                                    System.out.println("sid= " + sid + " id= " + i.getYourId());
                                    ((Connection) i).out.println("[" + id + "] " + name + ": " + str);
                                }
                            } else {

                                ((Connection) i).out.println("[" + id + "] " + name + ": " + str);
                            }


                        }
                    }

                }

                synchronized (connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).out.println("[" + id + "] " + name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public int getYourId() {
            return id;
        }

        public String getYourName() {
            return name;
        }

        public void close() {
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
                System.err.println("Fehler!");
            }
        }
    }
}