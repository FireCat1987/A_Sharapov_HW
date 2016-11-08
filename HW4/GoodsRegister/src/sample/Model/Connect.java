package sample.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private final String name = "padmin";
    private final String password = "pass";
    private final String path = "jdbc:postgresql://localhost:5434/mydb";
    private Connection connection = null;

    public Connection ConnectBD()
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection= DriverManager.getConnection(path,name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(connection!=null){
            System.out.println("Success");
        }

        return connection;
    }

    public boolean IsConnection()
    {
        if(connection!=null){
            return true;
        }else {
            return false;
        }
    }

    public void connectClose()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
