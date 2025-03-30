import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager.initializeDatabase();

        while (true) {
            System.out.println("\n===== Booktracker Menu =====");
            System.out.println("1. Add user");
            System.out.println("2. Show mean user age");
            System.out.println("3. Update book title");
            System.out.println("4. Delete reading habit");
            System.out.println("5. Count users who read a book");
            System.out.println("6. Total pages read");
            System.out.println("7. Count users who read more than one book");
            System.out.println("8. Add 'Name' column to User table");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter user ID: ");
                    int userID = scanner.nextInt();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    UserManager.addUser(userID, age, gender);
                }
                case 2 -> StatisticsManager.getMeanUserAge();
                case 3 -> {
                    System.out.print("Enter old book title: ");
                    String oldTitle = scanner.nextLine();
                    System.out.print("Enter new book title: ");
                    String newTitle = scanner.nextLine();
                    BookManager.updateBookTitle(oldTitle, newTitle);
                }
                case 4 -> {
                    System.out.print("Enter habit ID to delete: ");
                    int habitID = scanner.nextInt();
                    BookManager.deleteReadingHabit(habitID);
                }
                case 5 -> {
                    System.out.print("Enter book title: ");
                    String book = scanner.nextLine();
                    StatisticsManager.getUserCountByBook(book);
                }
                case 6 -> StatisticsManager.getTotalPagesRead();
                case 7 -> StatisticsManager.getUsersWithMultipleBooks();
                case 8 -> UserManager.addNameColumnIfNotExists();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
