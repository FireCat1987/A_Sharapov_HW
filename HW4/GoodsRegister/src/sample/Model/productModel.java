package sample.Model;

public class productModel {
    private int productId;
    private String productName;
    private String productDesc;
    private int productStockId;


    public productModel(int id, String name, int stockId, String desc) {
        this.productId = id;
        this.productName = name;
        this.productDesc = desc;
        this.productStockId = stockId;
    }

    public productModel(int id, String name, int stockId) {
        this.productId = id;
        this.productName = name;
        this.productDesc = "";
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductStock() {
        return productStockId;
    }

    public void setProductStock(int productStockId) {
        this.productStockId = productStockId;
    }
}
