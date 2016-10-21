import java.util.Arrays;

public class Stock {
    private Product[] allStock;

    public Stock() {
        this.allStock = new Product[0];
    }

    private void resizeStock(int plusSize) {
        int newSize = plusSize + allStock.length;
        allStock = Arrays.copyOf(allStock, newSize);
    }

    public void addToStock(Product product) {
        resizeStock(1);
        allStock[allStock.length-1] = product;
        System.out.println("Добавлен 1 товар!");
    }
    public void addToStock(Product[] product) {
        resizeStock(product.length);
        System.arraycopy(product, 0, allStock, allStock.length-product.length, allStock.length);
        System.out.println("Добавлено " + product.length + " товаров!");
    }
    public Product[] getAllProduct() {
        return allStock;
    }
    public int getColProduct() {
        return allStock.length;
    }
    public static boolean contains(int[] a, int num) {
        for (int i : a) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }
    public String getStatToColor() {
        String[][] colors = new String[allStock.length][2];

        int k = 0;
        for (Product product : allStock) {
            colors[k][0] = product.getColor();
            colors[k][1] = String.valueOf(0);
            for (Product p : allStock) {
                if(colors[k][0].equals(p.getColor())) {
                    colors[k][1] = String.valueOf(new Integer(colors[k][1]) + 1);
                }
            }
            k++;
        }

        int n = colors.length;

        for ( int i = 0, m = 0; i != n; i++, n = m )
        {
            for ( int j = m = i + 1; j != n; j++ )
            {
                if ( colors[j][0] != colors[i][0] )
                {
                    if ( m != j ) colors[m] = colors[j];
                    m++;
                }
            }
        }
        if ( n != colors.length )
        {
            String[][] b = new String[n][2];
            for ( int i = 0; i < n; i++ ) b[i] = colors[i];
            colors = b;
        }
        String s = "";
        for ( String[] x : colors ) s += x[0] + " " + x[1] + "шт; ";
        return s;
    }
    public int getAverageWeight() {
        int aWeight=0;
        for (Product product : allStock) {
            aWeight += product.getWeight();
        }
        return aWeight/allStock.length;
    }
}
