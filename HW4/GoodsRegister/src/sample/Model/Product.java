package sample.Model;

public class Product {
    private int productId;
    private String productName;
    private Double productCost;
    private int productStockId;
    private String productStockName;


    public Product(int id, String name, int stockId, Double cost) {
        this.productId = id;
        this.productName = name;
        this.productCost = cost;
        this.productStockId = stockId;
    }

    public Product(int id, String name, Double cost, String productStockName) {
        this.productId = id;
        this.productName = name;
        this.productCost = cost;
        this.productStockName = productStockName;
    }

    public Product(String name, int stockId, Double cost) {
        this.productName = name;
        this.productCost = cost;
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

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    int getProductStock() {
        return productStockId;
    }

    public void setProductStock(int productStockId) {
        this.productStockId = productStockId;
    }

    public String getProductStockName() {
        return productStockName;
    }

    public void setProductStockName(String productStockName) {
        this.productStockName = productStockName;
    }
}
