import java.util.Arrays;

class Stock {
    private Product[] mAllStock;

    Stock() {
        this.mAllStock = new Product[0];
    }

    private void resizeStock(int mPlusSize) {
        int mNewSize = mPlusSize + mAllStock.length;
        mAllStock = Arrays.copyOf(mAllStock, mNewSize);
    }

    void addToStock(Product mProduct) {
        resizeStock(1);
        mAllStock[mAllStock.length - 1] = mProduct;
        //System.out.println("[INFO] Добавлен 1 товар!");
    }

    void addToStock(Product[] mProduct) {
        resizeStock(mProduct.length);
        System.arraycopy(mProduct, 0, mAllStock, mAllStock.length - mProduct.length, mProduct.length);
        //System.out.println("[INFO] Добавлено " + product.length + " товаров!");
    }

    Product[] getAllProduct() {
        return mAllStock;
    }

    int getColProduct() {
        return mAllStock.length;
    }

    String getStatToColor() {
        if (mAllStock.length == 0) return "Значения цветов отсутствуют";
        String[][] mColors = new String[mAllStock.length][2];

        int k = 0;
        for (Product product : mAllStock) {
            mColors[k][0] = product.getColor();
            mColors[k][1] = String.valueOf(0);
            for (Product p : mAllStock) {
                if (mColors[k][0].equals(p.getColor())) {
                    mColors[k][1] = String.valueOf(new Integer(mColors[k][1]) + 1);
                }
            }
            k++;
        }

        int n = mColors.length;

        for (int i = 0, m; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (mColors[j][0].equals(mColors[i][0])) {
                    if (m != j) mColors[m] = mColors[j];
                    m++;
                }
            }
        }
        if (n != mColors.length) {
            String[][] b = new String[n][2];
            System.arraycopy(mColors, 0, b, 0, n);
            mColors = b;
        }
        String s = "";
        for (String[] x : mColors) s += x[0] + " " + x[1] + "шт; ";
        return s;
    }

    int getAverageWeight() {
        if (mAllStock.length == 0) return 0;
        int mSumWeight = 0;
        for (Product product : mAllStock) {
            mSumWeight += product.getWeight();
        }
        return mSumWeight / mAllStock.length;
    }

    int delAllProduct() {
        int mColProduct = getColProduct();
        this.mAllStock = new Product[0];
        return mColProduct;
    }
}
