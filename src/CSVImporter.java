import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {

    public static void importUsersFromCSV(String filePath) {
        String sql = "INSERT INTO User(userID, age, gender) VALUES (?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection conn = DatabaseManager.connect()) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int userID = Integer.parseInt(values[0]);
                int age = Integer.parseInt(values[1]);
                String gender = values[2];

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, userID);
                    pstmt.setInt(2, age);
                    pstmt.setString(3, gender);
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Users imported successfully.");
        } catch (IOException | SQLException e) {
            System.out.println("Error importing users: " + e.getMessage());
        }
    }

    public static void importReadingHabitsFromCSV(String filePath) {
        String sql = "INSERT INTO ReadingHabit(habitID, userID, pagesRead, book, submissionMoment) VALUES (?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection conn = DatabaseManager.connect()) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 5);
                int habitID = Integer.parseInt(values[0]);
                int userID = Integer.parseInt(values[1]);
                int pagesRead = Integer.parseInt(values[2]);
                String book = values[3];
                String submissionMoment = values[4];

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, habitID);
                    pstmt.setInt(2, userID);
                    pstmt.setInt(3, pagesRead);
                    pstmt.setString(4, book);
                    pstmt.setString(5, submissionMoment);
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Reading habits imported successfully.");
        } catch (IOException | SQLException e) {
            System.out.println("Error importing reading habits: " + e.getMessage());
        }
    }
}

