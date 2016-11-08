abstract class Product {
    private int mPrice;
    private int mWeight;
    private String mColor;

    Product(int mPrice, int mWeight, String mColor) {
        this.mPrice = mPrice;
        this.mWeight = mWeight;
        this.mColor = mColor;
    }

    int getPrice() {
        return mPrice;
    }


    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    int getWeight() {
        return mWeight;
    }

    public void setWeight(int mWeight) {
        this.mWeight = mWeight;
    }

    String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }
}
