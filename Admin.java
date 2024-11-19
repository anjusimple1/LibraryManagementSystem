import java.sql.Connection;  // Import the Connection class
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    
    // Method to validate admin credentials
    public static boolean validate(String username, String password) {
        try (Connection conn = DatabaseConnector.connect()) {  // Create a database connection
            String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'admin'";
            PreparedStatement stmt = conn.prepareStatement(query);  // Prepare the query
            stmt.setString(1, username);  // Set the username parameter
            stmt.setString(2, password);  // Set the password parameter
            ResultSet rs = stmt.executeQuery();  // Execute the query and get the result set
            return rs.next();  // Return true if the credentials are valid
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Print error message if any exception occurs
        }
        return false;  // Return false if credentials are invalid
    }

    // Method to add a new book to the database
    public static void addBook(String title, String author) {
        try (Connection conn = DatabaseConnector.connect()) {  // Create a database connection
            String query = "INSERT INTO books (title, author, is_available) VALUES (?, ?, 1)";  // SQL query to insert book
            PreparedStatement stmt = conn.prepareStatement(query);  // Prepare the query
            stmt.setString(1, title);  // Set the title parameter
            stmt.setString(2, author);  // Set the author parameter
            stmt.executeUpdate();  // Execute the query to add the book
            System.out.println("Book added successfully!");  // Success message
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Print error message if any exception occurs
        }
    }
}
