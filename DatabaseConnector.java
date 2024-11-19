import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // Define the URL of the SQLite database
    private static final String URL = "jdbc:sqlite:library.db";

    // Method to establish a connection to the database
    public static Connection connect() {
        Connection conn = null;  // Declare a connection object to hold the connection
        try {
            // Attempt to establish a connection to the database using the URL
            conn = DriverManager.getConnection(URL);
            
            // Print a message if the connection is successful
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            // If the connection fails, print the error message
            System.out.println("Connection failed: " + e.getMessage());
        }
        // Return the connection object (null if connection failed)
        return conn;
    }
}
