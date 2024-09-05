package cooo;

import cooo.utils.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Get the database connection
        Connection connection = DatabaseConnection.getConnection();

        // You can run a simple query or just check the connection
        if (connection != null) {
            System.out.println("Connection successful!");
        }

        // Close the connection after the operations
        DatabaseConnection.closeConnection();
    }
}
