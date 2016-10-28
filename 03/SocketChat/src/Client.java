import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private DatagramSocket ds;
    private Scanner scan = new Scanner(System.in);
    Client() {
        //
        //System.out.println("IP Adresse (Localhost 127.0.0.1).");
        //System.out.println("Format: xxx.xxx.xxx.xxx");
        //String ip = "127.0.0.1";//scan.nextLine();

        try {
            ds = new DatagramSocket(9090);
            byte [] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            System.out.println(packet.getAddress().getHostAddress() + " " + new String(packet.getData(), 0, packet.getLength()));
            Socket client = new Socket(packet.getAddress().getHostAddress(), 6666);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Имя в чате");
            out.println(scan.nextLine());
            System.out.println(in.readLine());
            Resender resend = new Resender();
            resend.start();
            String str = "";
            while (!str.equals("exit")) {
                str = scan.nextLine();
                out.println(str);
            }
            resend.setStop();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Ошибка!");
        }
    }

    private class Resender extends Thread {
        private boolean stoped;

        void setStop() {
            stoped = true;
        }

        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.err.println("Ошибка!");
                e.printStackTrace();
            }
        }
    }
}