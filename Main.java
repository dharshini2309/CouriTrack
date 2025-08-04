import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("=== CourierTrack Login ===");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        String role = userService.login(username, password);
        if (role == null) {
            System.out.println("❌ Login failed. Exiting...");
            return;
        }

        System.out.println("✅ Login successful! Role: " + role);
        CourierService service = new CourierService();
        int choice;

        do {
            System.out.println("\n--- CourierTrack Management System ---");
            System.out.println("1. Add Courier");
            System.out.println("2. Track Courier");
            System.out.println("3. Update Status");
            System.out.println("4. Show All Couriers");
            System.out.println("5. Search Couriers");
            System.out.println("6. Export to CSV");
            if (role.equals("admin")) {
                System.out.println("7. Delete Courier");
            }
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Tracking ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Sender Name: ");
                    String sender = sc.nextLine();
                    System.out.print("Enter Receiver Name: ");
                    String receiver = sc.nextLine();
                    service.addCourier(id, sender, receiver);
                    break;
                case 2:
                    System.out.print("Enter Tracking ID to Track: ");
                    String trackId = sc.nextLine();
                    service.trackCourier(trackId);
                    break;
                case 3:
                    System.out.print("Enter Tracking ID to Update: ");
                    String updateId = sc.nextLine();
                    System.out.print("Enter New Status: ");
                    String status = sc.nextLine();
                    service.updateStatus(updateId, status);
                    break;
                case 4:
                    service.showAllCouriers();
                    break;
                case 5:
                    System.out.print("Search by (1) Sender (2) Receiver (3) Status: ");
                    int opt = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter search value: ");
                    String val = sc.nextLine();
                    service.searchCouriers(opt, val);
                    break;
                case 6:
                    ExportUtil.exportToCSV();
                    break;
                case 7:
                    if (role.equals("admin")) {
                        System.out.print("Enter Tracking ID to Delete: ");
                        String delId = sc.nextLine();
                        service.deleteCourier(delId);
                    } else {
                        System.out.println("❌ Access denied.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}