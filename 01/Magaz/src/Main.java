public class Main {
    public static void main(String[] args) {
        Stock magStock = new Stock();
        Product[] products = new Product[3];
        products[0] = new Tv(1,3,"black",5);
        products[1] = new Phone(2,7,"black",9,10);
        products[2] = new Radio(4,3,"black","FM");
        magStock.addToStock(products);
        Tv tv = new Tv(2,4,"white",5);
        magStock.addToStock(tv);
        System.out.println("Средний вес: " + magStock.getAverageWeight());
        System.out.println("Количество продуктов на складе: " + magStock.getColProduct());
        System.out.println("Статистика по цветам: " + magStock.getStatToColor());
    }
}
