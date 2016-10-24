import java.io.*;

import java.net.Socket;

import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;


    public Client() {
        Scanner scan = new Scanner(System.in);

        System.out.println("IP Adresse (Localhost 127.0.0.1).");
        System.out.println("Format: xxx.xxx.xxx.xxx");

        String ip = "127.0.0.1";//scan.nextLine();

        try {

            socket = new Socket(ip, 6666);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

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
        } catch (Exception e) {
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
            System.err.println("Fehler!");
        }
    }


    private class Resender extends Thread {

        private boolean stoped;


        public void setStop() {
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
                System.err.println("Fehler");
                e.printStackTrace();
            }
        }
    }

}