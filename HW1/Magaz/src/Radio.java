class Radio extends Product {
    private String mRange;

    Radio(int mPrice, int mWeight, String mColor, String mRange) {
        super(mPrice, mWeight, mColor);
        this.mRange = mRange;
    }

    @Override
    public String toString() {
        return "Radio{" +
                "price=" + getPrice() +
                ", weight=" + getWeight() +
                ", color=" + getColor() +
                ", range='" + mRange + '\'' +
                '}';
    }

    public String getRange() {
        return mRange;
    }

    public void setRange(String mRange) {
        this.mRange = mRange;
    }
}
