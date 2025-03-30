import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsManager {

    public static void getMeanUserAge() {
        String sql = "SELECT AVG(age) AS mean_age FROM User";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Mean age of users: " + rs.getDouble("mean_age"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving mean age: " + e.getMessage());
        }
    }

    public static void getUserCountByBook(String bookTitle) {
        String sql = "SELECT COUNT(DISTINCT userID) AS user_count FROM ReadingHabit WHERE book = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookTitle);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Users who read '" + bookTitle + "': " + rs.getInt("user_count"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user count: " + e.getMessage());
        }
    }

    public static void getTotalPagesRead() {
        String sql = "SELECT SUM(pagesRead) AS total_pages FROM ReadingHabit";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Total pages read by all users: " + rs.getInt("total_pages"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving total pages: " + e.getMessage());
        }
    }

    public static void getUsersWithMultipleBooks() {
        String sql = "SELECT COUNT(*) AS user_count FROM (" +
                "SELECT userID FROM ReadingHabit GROUP BY userID HAVING COUNT(DISTINCT book) > 1)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Users who read more than one book: " + rs.getInt("user_count"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving multi-book users: " + e.getMessage());
        }
    }
}