package sample.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Request {
    private Connection connection = null;

    public Request(Connection connection) {
        this.connection = connection;
    }

    public boolean addStock(String stockName) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE " + stockName + " (id SERIAL PRIMARY KEY NOT NULL, thing_name VARCHAR(30) NOT NULL, model VARCHAR(30) NOT NULL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delStock(String stockName) {
        return false;
    }

    public boolean updateStock(String stockName) {
        return false;
    }

    public boolean addProduct(String ProductName) {
        return false;
    }

    public boolean delProduct(String ProductName) {
        return false;
    }

    public boolean updateProduct(String ProductName) {
        return false;
    }

    public Product[] getProductsByStock(String stockName) {
        return null;
    }

    public Stock[] getStocks() {
        return null;
    }

    public Product findProducts(String ProductName) {
        return null;
    }
}
