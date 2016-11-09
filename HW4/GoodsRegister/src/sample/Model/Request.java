package sample.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Request {
    private Connection connection = null;

    public Request(Connection connection) {
        this.connection = connection;
    }

    public boolean addStock(String stockName) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO stocks (name) VALUES (" + "'" + stockName + "') ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delStock(int stockId) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM product WHERE idstock= '" + stockId + "'");
            statement.execute("DELETE FROM stocks WHERE id= " + "'" + stockId + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStock(Stock stock) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE stocks SET name='" + stock.getStockName() + "' WHERE id= " + "'" + stock.getStockId() + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProduct(Product product) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO product (name, cost, idstock) VALUES (" + "'" + product.getProductName() + "','" + product.getProductCost() + "','" + product.getProductStock() + "') ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delProduct(int ProductId) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM product WHERE id= " + "'" + ProductId + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE product SET name='" + product.getProductName() + "', cost='" + product.getProductCost() + "' WHERE id= " + "'" + product.getProductId() + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getProductsByStock(String stockName) {
        List<Product> resultProduct = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet result1 = statement.executeQuery("SELECT * FROM stocks WHERE name= '" + stockName + "'");
            int stockId = 0;


            while (result1.next()) {
                stockId = result1.getInt("id");

            }
            if (stockId == 0) return resultProduct;
            ResultSet result2 = statement.executeQuery("SELECT * FROM product WHERE idstock= '" + stockId + "'");
            while (result2.next()) {

                resultProduct.add(new Product(result2.getInt("id"), result2.getString("name"), stockId, result2.getDouble("cost")));
            }
            return resultProduct;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultProduct;
    }

    public List<Stock> getStocks() {
        List<Stock> resultStock = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stocks");

            while (result.next()) {
                resultStock.add(new Stock(result.getInt("id"), result.getString("name")));

            }

            return resultStock;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultStock;
    }

    public List<Product> findProducts(String ProductName) {
        List<Product> resultProduct = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT product.id, product.name, product.cost, stocks.name AS sname FROM product INNER JOIN stocks ON (product.idstock = stocks.id)  WHERE lower(product.name) LIKE '%" + ProductName.toLowerCase() + "%'");
            System.out.println(result.toString());
            while (result.next()) {
                System.out.println(result.getInt("id") + "/" + result.getString("name") + "/" + result.getDouble("cost") + "/" + result.getString("sname"));
                resultProduct.add(new Product(result.getInt("id"), result.getString("name"), result.getDouble("cost"), result.getString("sname")));
            }

            return resultProduct;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultProduct;
    }
}
