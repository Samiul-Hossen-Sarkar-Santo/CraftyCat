package com.craftycat.normalClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connection;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/craftycat", "root", "2254901079");

        if (connection != null) {
            System.out.println("OK");
        } else {
            System.out.println("error");
        }
        return connection;
    }
}
