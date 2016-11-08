import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Stock sMagStock;

    public static void main(String[] args) {
        sMagStock = new Stock();
        Scanner sScanner = new Scanner(System.in);
        System.out.println("Выберите действие:");
        System.out.println("s - статистика, a - автоматическое добавление 3 товаров, l - вывести все товары, с - очистить склад, exit - выход;");
        while(true) {
            String mInput = sScanner.nextLine();
            final Random mRandom = new Random();
            switch (mInput) {
                case "s":
                    printStat();
                    break;
                case "a":
                    addProduct();
                    break;
                case "l":
                    printListProduct();
                    break;
                case "с":
                    clearStock();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Данное действие не определено! Попробуйте ещё раз...");
                    System.out.println("s - статистика, a - автоматическое добавление 3 товаров, l - вывести все товары, с - очистить склад, exit - выход;");
                    break;
            }
        }
    }

    private static void clearStock() {
        System.out.println("------------------/Очистка базы/----------------");
        System.out.println("Удалено продуктов: " + sMagStock.delAllProduct());
        System.out.println("------------------------------------------------");

    }

    private static void printStat() {
        System.out.println("------------------/Статистика/------------------");
        System.out.println("Средний вес: " + sMagStock.getAverageWeight() + "; Количество продуктов на складе: " + sMagStock.getColProduct());
        System.out.println("Статистика по цветам: " + sMagStock.getStatToColor());
        System.out.println("------------------------------------------------");
    }

    private static void printListProduct() {
        Product[] mProduct = sMagStock.getAllProduct();
        System.out.println("----------/Список продуктов на складе/----------");
        System.out.println("Продуктов на складе:" + mProduct.length);
        for (Product product : mProduct) {
            System.out.println(product.toString());
        }
        System.out.println("------------------------------------------------");
    }

    private static void addProduct() {
        final Random mRandom = new Random();
        System.out.println("----------/Список продуктов на складе/----------");
        int mOldColProduct = sMagStock.getColProduct();
        Product[] products = new Product[2];
        products[0] = new Phone(mRandom.nextInt(10), mRandom.nextInt(10), (mRandom.nextInt(2) == 1 ? "black" : "white"), mRandom.nextInt(10), mRandom.nextInt(10));
        products[1] = new Radio(mRandom.nextInt(10), mRandom.nextInt(10), (mRandom.nextInt(2) == 0 ? "black" : "yellow"), "FM");
        sMagStock.addToStock(products);
        Tv tv = new Tv(mRandom.nextInt(10), mRandom.nextInt(10), (mRandom.nextInt(2) == 1 ? "white" : "yellow"), mRandom.nextInt(10));
        sMagStock.addToStock(tv);
        System.out.println("Добавлено продуктов: " + (sMagStock.getColProduct() - mOldColProduct));
        System.out.println("------------------------------------------------");
    }
}
