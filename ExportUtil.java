import java.io.FileWriter;
import java.sql.*;

public class ExportUtil {
    public static void exportToCSV() {
        String query = "SELECT * FROM couriers";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             FileWriter writer = new FileWriter("couriers_export.csv")) {

            writer.write("TrackingID,Sender,Receiver,Status\n");
            while (rs.next()) {
                writer.write(rs.getString("tracking_id") + "," +
                        rs.getString("sender_name") + "," +
                        rs.getString("receiver_name") + "," +
                        rs.getString("status") + "\n");
            }
            System.out.println("✅ Data exported to couriers_export.csv");

        } catch (Exception e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}