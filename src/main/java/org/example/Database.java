package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static Database instance;
    private Connection connection;

    private final String connectionUrl = "jdbc:h2:./DB";

    private Database() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }
}




