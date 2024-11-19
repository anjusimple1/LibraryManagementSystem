import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Display the welcome message and menu options
        System.out.println("Welcome to the Library Management System");
        System.out.println("1. Admin Login");
        System.out.println("2. Register User");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Exit");
        
        // Infinite loop to keep the program running until the user exits
        while (true) {
            System.out.print("Choose an option: ");  // Ask the user to choose an option
            int choice = scanner.nextInt();  // Read the user's choice

            // Handle different choices based on user input
            switch (choice) {
                case 1:
                    // Admin login process
                    System.out.print("Enter admin username: ");
                    String username = scanner.next();
                    System.out.print("Enter admin password: ");
                    String password = scanner.next();
                    // Validate admin credentials
                    if (Admin.validate(username, password)) {
                        System.out.println("Admin logged in successfully!");
                        // If login is successful, add a book
                        System.out.print("Enter book title: ");
                        String title = scanner.next();
                        System.out.print("Enter book author: ");
                        String author = scanner.next();
                        Admin.addBook(title, author);  // Add book to the library
                    } else {
                        System.out.println("Invalid admin credentials.");  // If credentials are invalid
                    }
                    break;

                case 2:
                    // User registration process
                    System.out.print("Enter username: ");
                    String newUser = scanner.next();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.next();
                    System.out.print("Enter role (user/admin): ");
                    String role = scanner.next();
                    // Register the new user
                    UserManagement.registerUser(newUser, newPassword, role);
                    break;

                case 3:
                    // Borrow a book
                    System.out.print("Enter your user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    // Borrow the book
                    Borrowing.borrowBook(userId, bookId);
                    break;

                case 4:
                    // Return a book
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    // Return the borrowed book
                    Borrowing.returnBook(returnBookId);
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting... Thank you!");
                    return;  // Exit the program

                default:
                    // If the user enters an invalid choice
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
