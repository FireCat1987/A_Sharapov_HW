public class Radio extends Product {
    private String range;

    public Radio(int price, int weight, String color, String range) {
        super(price, weight, color);
        this.range = range;
    }

    @Override
    public String toString() {
        return "Radio{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", range='" + range + '\'' +
                '}';
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
