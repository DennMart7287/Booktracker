import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    public static void addUser(int userID, int age, String gender) {
        String sql = "INSERT INTO User(userID, age, gender) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.executeUpdate();

            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public static void addNameColumnIfNotExists() {
        String checkColumnSQL = "PRAGMA table_info(User);";
        String addColumnSQL = "ALTER TABLE User ADD COLUMN Name TEXT;";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkColumnSQL);
             ResultSet rs = checkStmt.executeQuery()) {

            boolean nameExists = false;
            while (rs.next()) {
                if ("Name".equalsIgnoreCase(rs.getString("name"))) {
                    nameExists = true;
                    break;
                }
            }

            if (!nameExists) {
                try (PreparedStatement addStmt = conn.prepareStatement(addColumnSQL)) {
                    addStmt.execute();
                    System.out.println("'Name' column added to User table.");
                }
            } else {
                System.out.println("'Name' column already exists.");
            }

        } catch (SQLException e) {
            System.out.println("Error modifying User table: " + e.getMessage());
        }
    }
}
