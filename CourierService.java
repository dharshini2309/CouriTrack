import java.sql.*;

public class CourierService {

    public void addCourier(String trackingId, String sender, String receiver) {
        String query = "INSERT INTO couriers (tracking_id, sender_name, receiver_name) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, trackingId);
            stmt.setString(2, sender);
            stmt.setString(3, receiver);
            stmt.executeUpdate();
            System.out.println("Courier added successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateStatus(String trackingId, String newStatus) {
        String updateCourier = "UPDATE couriers SET status = ? WHERE tracking_id = ?";
        String insertLog = "INSERT INTO status_log (tracking_id, status) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(updateCourier);
             PreparedStatement stmt2 = conn.prepareStatement(insertLog)) {

            stmt1.setString(1, newStatus);
            stmt1.setString(2, trackingId);
            int rows = stmt1.executeUpdate();

            if (rows > 0) {
                stmt2.setString(1, trackingId);
                stmt2.setString(2, newStatus);
                stmt2.executeUpdate();
                System.out.println("Status updated and logged!");
            } else {
                System.out.println("Courier not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void trackCourier(String trackingId) {
        String query = "SELECT * FROM couriers WHERE tracking_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, trackingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Tracking ID: " + rs.getString("tracking_id"));
                System.out.println("Sender: " + rs.getString("sender_name"));
                System.out.println("Receiver: " + rs.getString("receiver_name"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("Courier not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showAllCouriers() {
        String query = "SELECT * FROM couriers";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("\nTracking ID: " + rs.getString("tracking_id"));
                System.out.println("Sender: " + rs.getString("sender_name"));
                System.out.println("Receiver: " + rs.getString("receiver_name"));
                System.out.println("Status: " + rs.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchCouriers(int option, String value) {
        String column = switch (option) {
            case 1 -> "sender_name";
            case 2 -> "receiver_name";
            case 3 -> "status";
            default -> null;
        };
        if (column == null) {
            System.out.println("Invalid option.");
            return;
        }

        String query = "SELECT * FROM couriers WHERE " + column + " LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + value + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nTracking ID: " + rs.getString("tracking_id"));
                System.out.println("Sender: " + rs.getString("sender_name"));
                System.out.println("Receiver: " + rs.getString("receiver_name"));
                System.out.println("Status: " + rs.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteCourier(String trackingId) {
        String query = "DELETE FROM couriers WHERE tracking_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, trackingId);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Courier deleted successfully!");
            else System.out.println("Courier not found.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}