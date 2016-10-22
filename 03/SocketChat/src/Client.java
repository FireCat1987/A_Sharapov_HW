import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        int port = 6666;
        try {
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            String[] myip = InetAddress.getLocalHost().toString().split("/");
            System.out.println(InetAddress.getLocalHost());
            Socket s = new Socket(ip, port);
            InputStream sin = s.getInputStream();
            OutputStream sout = s.getOutputStream();
            BufferedWriter bin = new BufferedWriter(new OutputStreamWriter(sout));
            BufferedReader bout = new BufferedReader(new InputStreamReader(sin));




            while (true) {
                Scanner sc = new Scanner(System.in);
                bin.write("[Аркадий " + myip[1] + "]:" + sc.nextLine());
                bin.newLine();
                bin.flush();
                String line = bout.readLine();
                System.out.println(line);

            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}
