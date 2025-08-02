import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourierService service = new CourierService();
        int choice;

        do {
            System.out.println("\n--- CourierTrack Management System ---");
            System.out.println("1. Add Courier");
            System.out.println("2. Track Courier");
            System.out.println("3. Update Status");
            System.out.println("4. Show All Couriers");
            System.out.println("5. Exit");
            System.out.println("6. Delete Courier");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

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
                    System.out.println("Exiting... Goodbye!");
                    break;
                case 6:
                    System.out.print("Enter Tracking ID to Delete: ");
                    String delId = sc.nextLine();
                    service.deleteCourier(delId);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }
}