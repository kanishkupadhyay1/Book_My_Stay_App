import java.util.*;
/**
 * Class Add on service
 *
 *Use Case 10: Booking Cancellation & Inventory Rollback

 *
 * @author Kanishk Upadhyay
 * @version 10.0
 */


 class CancellationService {

    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
        System.out.println("Booking registered: " + reservationId + " -> " + roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation does not exist.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        inventory.releaseRoom(roomType);

        releasedRoomIds.push(reservationId);

        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully: " + reservationId);
    }

    public void showRollbackHistory() {

        if (releasedRoomIds.isEmpty()) {
            System.out.println("No cancellations recorded.");
            return;
        }

        System.out.println("Rollback History:");

        while (!releasedRoomIds.isEmpty()) {
            System.out.println(releasedRoomIds.pop());
        }
    }
}


 class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("single", 5);
        inventory.put("double", 3);
        inventory.put("suite", 2);
    }

    public void releaseRoom(String roomType) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, inventory.get(roomType) + 1);
        }
    }

    public void showInventory() {
        System.out.println(inventory);
    }
}




public class Book_My_Stay_App {

    public static void main(String args[]){
        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        service.registerBooking("R101", "single");
        service.registerBooking("R102", "double");
        service.registerBooking("R103", "single");

        System.out.println("\nCancelling bookings...\n");

        service.cancelBooking("R102", inventory);
        service.cancelBooking("R101", inventory);

        System.out.println();

        service.showRollbackHistory();
    }
}