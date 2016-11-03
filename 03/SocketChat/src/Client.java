import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.Callable;

class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket client;
    private int stepConnect = 0;

    Client() {
        try {
            connect();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Имя в чате");
            Scanner scan = new Scanner(System.in);
            out.println(scan.nextLine());
            System.out.println(in.readLine());
            Resender resend = new Resender();
            resend.start();
            String str = "";
            while (!str.equals("//exit")) {
                str = scan.nextLine();
                if(str.equals("//command")) System.out.println("//list - список всех участников, [id] сообщение  - отправка приватного сообщения");
                out.println(str);
            }
            resend.setStop();
        } catch (IOException e) {
            System.err.println("Во время настройки подключения возникла ошибка ввода/вывода!");
        } finally {
            close();
        }
    }

    private void close() {
        out.close();
        System.exit(0);
    }

    private void connect() {
        try {
            System.out.println("Подключение к серверу (попытка №" + stepConnect + ").");
            stepConnect += 1;
            System.out.println("Поиск сервера в сети...");
            DatagramSocket ds = new DatagramSocket(9090);
            ds.setBroadcast(true);
            byte [] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            ds.close();
            client = new Socket(packet.getAddress().getHostAddress(),6666);
            System.out.println("Поиск и подключение к серверу выполнено успешно. На сервере находится " + new String(packet.getData(), 0, packet.getLength()) + " посетителей!");
        } catch (IOException e) {
            System.err.println("Ощибка при подключении к серверу!");
            if(stepConnect < 3) {
                connect();
            } else {
                System.err.println("После 3-х неудачных попыток подключения к серверу работа клиента заврешается!");
                System.exit(0);
            }
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
                System.err.println("Сервер неожиданно разорвал соединение!");
                System.exit(0);
            }
        }
    }
}