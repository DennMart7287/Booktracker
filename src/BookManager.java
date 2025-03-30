import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookManager {

    public static void updateBookTitle(String oldTitle, String newTitle) {
        String sql = "UPDATE ReadingHabit SET book = ? WHERE book = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setString(2, oldTitle);
            int affectedRows = pstmt.executeUpdate();

            System.out.println(affectedRows + " record(s) updated.");
        } catch (SQLException e) {
            System.out.println("Error updating book title: " + e.getMessage());
        }
    }

    public static void deleteReadingHabit(int habitID) {
        String sql = "DELETE FROM ReadingHabit WHERE habitID = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, habitID);
            int affectedRows = pstmt.executeUpdate();

            System.out.println(affectedRows + " record(s) deleted.");
        } catch (SQLException e) {
            System.out.println("Error deleting reading habit: " + e.getMessage());
        }
    }
}
