import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrowing {

    // Method to borrow a book by a user (userId) and book (bookId)
    public static void borrowBook(int userId, int bookId) {
        try (Connection conn = DatabaseConnector.connect()) {
            // SQL query to check if the book is available
            String checkQuery = "SELECT is_available FROM books WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, bookId);  // Set the bookId in the query
            ResultSet rs = checkStmt.executeQuery();

            // If the book is available (is_available = 1), borrow the book
            if (rs.next() && rs.getInt("is_available") == 1) {
                // Insert a record into borrowed_books with userId, bookId, and current date
                String borrowQuery = "INSERT INTO borrowed_books (user_id, book_id, borrow_date) VALUES (?, ?, DATE('now'))";
                PreparedStatement borrowStmt = conn.prepareStatement(borrowQuery);
                borrowStmt.setInt(1, userId);  // Set the userId in the query
                borrowStmt.setInt(2, bookId);  // Set the bookId in the query
                borrowStmt.executeUpdate();

                // Update the book's availability to 0 (not available) in the books table
                String updateQuery = "UPDATE books SET is_available = 0 WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, bookId);  // Set the bookId to update its availability
                updateStmt.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                // If the book is not available, print a message
                System.out.println("Book is not available.");
            }
        } catch (SQLException e) {
            // Print the error message if there is an issue with the database operation
            System.out.println(e.getMessage());
        }
    }

    // Method to return a borrowed book
    public static void returnBook(int bookId) {
        try (Connection conn = DatabaseConnector.connect()) {
            // Update the book's availability to 1 (available) in the books table
            String returnQuery = "UPDATE books SET is_available = 1 WHERE id = ?";
            PreparedStatement returnStmt = conn.prepareStatement(returnQuery);
            returnStmt.setInt(1, bookId);  // Set the bookId to mark it as available again
            returnStmt.executeUpdate();

            // Set the return date for the book in the borrowed_books table
            String updateReturnDateQuery = "UPDATE borrowed_books SET return_date = DATE('now') WHERE book_id = ? AND return_date IS NULL";
            PreparedStatement updateReturnStmt = conn.prepareStatement(updateReturnDateQuery);
            updateReturnStmt.setInt(1, bookId);  // Set the bookId to update the return date
            updateReturnStmt.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            // Print the error message if there is an issue with the database operation
            System.out.println(e.getMessage());
        }
    }
}
