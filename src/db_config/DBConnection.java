package db_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = "password";

    private static Connection connection = null;

    // Get the DB connection (singleton)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load JDBC driver (optional in modern Java versions)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Connect to the database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Close the connection when done
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("🔌 Database connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
}
