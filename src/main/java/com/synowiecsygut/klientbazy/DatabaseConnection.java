package com.synowiecsygut.klientbazy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

    private static final String DB_ADDRESS = "localhost";
    private static final int DB_PORT = 1522;
    private static final String DB_NAME = "xe";
    private static final String USERNAME = "c##klientbaza";
    private static final String PASSWORD = "password";

    private Connection connection;

    public DatabaseConnection() throws SQLException {
        connectToDatabase();
    }

    private void connectToDatabase() throws SQLException {
        try {
            String url = "jdbc:oracle:thin:@" + DB_ADDRESS + ":" + DB_PORT + ":" + DB_NAME;
            this.connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to establish a connection.");
            throw e;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnectionValid() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() throws SQLException {
        if (isConnectionValid()) {
            connection.close();
            System.out.println("Connection closed.");
        }
    }
}
