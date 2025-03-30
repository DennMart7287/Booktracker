import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:booktracker.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createUserTable = """
            CREATE TABLE IF NOT EXISTS User (
                userID INTEGER PRIMARY KEY,
                age INTEGER,
                gender TEXT
            );
        """;

        String createReadingHabitTable = """
            CREATE TABLE IF NOT EXISTS ReadingHabit (
                habitID INTEGER PRIMARY KEY,
                userID INTEGER,
                pagesRead INTEGER,
                book TEXT,
                submissionMoment TEXT,
                FOREIGN KEY(userID) REFERENCES User(userID)
            );
        """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createReadingHabitTable);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }
}

