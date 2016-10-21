public class Tv extends Product {
    private int diagonal;

    public Tv(int price, int weight, String color, int diagonal) {
        super(price, weight, color);
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", diagonal=" + diagonal +
                '}';
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
}
