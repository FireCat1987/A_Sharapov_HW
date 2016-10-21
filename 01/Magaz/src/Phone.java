public class Phone extends Product {
    private int memory;
    private int diagonal;

    public Phone(int price, int weight, String color, int memory, int diagonal) {
        super(price, weight, color);
        this.memory = memory;
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", memory=" + memory +
                ", diagonal=" + diagonal +
                '}';
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
}
