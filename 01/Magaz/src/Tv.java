class Tv extends Product {
    private int mDiagonal;

    Tv(int mPrice, int mWeight, String mColor, int mDiagonal) {
        super(mPrice, mWeight, mColor);
        this.mDiagonal = mDiagonal;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", diagonal=" + mDiagonal +
                '}';
    }

    public int getDiagonal() {
        return mDiagonal;
    }

    public void setDiagonal(int mDiagonal) {
        this.mDiagonal = mDiagonal;
    }
}
