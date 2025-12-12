package edu.ijse.mvc.fx.shopmanagementsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ShopManagementSystem?connectTimeout=10000&socketTimeout=30000";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    private DBConnection() {}

    public static DBConnection getInstance() {
        return new DBConnection();
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
