package sample.Model;

public class Product {
    private int productId;
    private String productName;
    private Double productPrice;
    private int productStockId;


    public Product(int id, String name, int stockId, Double price) {
        this.productId = id;
        this.productName = name;
        this.productPrice = price;
        this.productStockId = stockId;
    }

    public Product(int id, String name, int stockId) {
        this.productId = id;
        this.productName = name;
        this.productPrice = 0.0;
        this.productStockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStockId;
    }

    public void setProductStock(int productStockId) {
        this.productStockId = productStockId;
    }
}
