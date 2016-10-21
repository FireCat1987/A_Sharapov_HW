abstract class Product {
    private int price;
    private int weight;
    private String color;

    public Product(int price, int weight, String color) {
        this.price = price;
        this.weight = weight;
        this.color = color;
    }

    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
