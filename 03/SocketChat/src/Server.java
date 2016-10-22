import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

    public static void main(String[] args) throws IOException {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting...");
            Socket s = ss.accept();
            System.out.println("client is there!");
            String[] myip = InetAddress.getLocalHost().toString().split("/");
            InputStream sin = s.getInputStream();
            OutputStream sout = s.getOutputStream();
            BufferedWriter bin = new BufferedWriter(new OutputStreamWriter(sout));
            BufferedReader bout = new BufferedReader(new InputStreamReader(sin));
            while (true) {
                String line = bout.readLine();
                System.out.println(line);
                Scanner sc = new Scanner(System.in);
                bin.write("[Аркадий " + myip[1] + "]:" + sc.nextLine());
                bin.newLine();
                bin.flush();


            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
