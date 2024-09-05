package cooo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/GestionBiblioversion2"; // Adjust the port and DB name
    private static final String USER = "postgres";
    private static final String PASSWORD = "hh180484";

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to connect to the PostgreSQL server.");
            }


        }
        return connection;
    }

    // Method to close connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
