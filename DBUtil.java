import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/CourierTrackDB";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = "Dharshu@23"; // replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Optional: Test connection directly
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("✅ Connected to database!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
