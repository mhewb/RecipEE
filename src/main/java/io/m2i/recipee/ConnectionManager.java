package io.m2i.recipee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_DATABASE = "jdbc:mysql://localhost:3306/RecipEE";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL-r00t!";

    private static Connection INSTANCE;

    private ConnectionManager() {
        // avoid instanciation
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Error during getConnection");
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.err.println("Error during driver loading");
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }

    public static void closeConnection() throws SQLException {
        if (INSTANCE != null && !INSTANCE.isClosed()) {
            INSTANCE.close();
        }
    }

}