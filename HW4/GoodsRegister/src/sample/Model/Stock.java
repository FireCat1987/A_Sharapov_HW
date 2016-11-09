package sample.Model;

public class Stock {
    private int stockId;
    private String stockName;
    private String stockDesc;

    public Stock(int id, String name) {
        this.stockId = id;
        this.stockName = name;
        this.stockDesc = "";
    }

    public Stock(int id, String name, String desc) {
        this.stockId = id;
        this.stockName = name;
        this.stockDesc = desc;
    }


    public String getStockName() {
        return this.stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getStockId() {
        return this.stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockDesc() {
        return this.stockDesc;
    }

    public void setStockDesc(String stockDesc) {
        this.stockDesc = stockDesc;
    }
}
