import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManagement {

    // Method to register a new user with username, password, and role
    public static void registerUser(String username, String password, String role) {
        // Establish a connection to the database
        Connection conn = DatabaseConnector.connect();

        // If the connection fails, print an error message and stop the registration
        if (conn == null) {
            System.out.println("Database connection failed. Cannot register user.");
            return;
        }

        try {
            // SQL query to check if the username already exists in the database
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);  // Set the username to check in the query

            // Execute the query and check if any matching username exists
            ResultSet rs = checkStmt.executeQuery();

            // If the username exists, print an error and stop the registration process
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: Username already exists. Please choose a different username.");
                return;
            }

            // SQL query to insert the new user into the 'users' table
            String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);  // Set the username for the new user
            insertStmt.setString(2, password);  // Set the password for the new user
            insertStmt.setString(3, role);      // Set the role for the new user

            // Execute the insert query to add the new user to the database
            insertStmt.executeUpdate();

            // Print a success message once the user is registered
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            // If there is an error during the database operations, print the error message
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
}

