import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Работать как Клиент или Сервер? (S(erver) / C(lient))");
        while(true) {
            String answer = in.nextLine();
            switch (answer.toLowerCase()){
                case "s":
                    new Server();
                    break;
                case "c":
                    new Client();
                    break;
                case "exit":
                    System.out.println("Выход из приложения");
                    System.exit(0);
                default:
                    System.out.println("Неверный ввод, попробуйте ещё раз!");
                    break;
            }
        }
    }
}
