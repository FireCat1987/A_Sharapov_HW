class Phone extends Product {
    private int mMemory;
    private int mDiagonal;

    Phone(int mPrice, int mWeight, String mColor, int mMemory, int mDiagonal) {
        super(mPrice, mWeight, mColor);
        this.mMemory = mMemory;
        this.mDiagonal = mDiagonal;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", memory=" + mMemory +
                ", diagonal=" + mDiagonal +
                '}';
    }

    public int getMemory() {
        return mMemory;
    }

    public void setMemory(int mMemory) {
        this.mMemory = mMemory;
    }

    public int getDiagonal() {
        return mDiagonal;
    }

    public void setDiagonal(int mDiagonal) {
        this.mDiagonal = mDiagonal;
    }
}
