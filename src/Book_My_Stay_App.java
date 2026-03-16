import java.util.*;
/**
 * Class Add on service
 *
 *Use Case 11: UC11 - Concurrent Booking Simulation

 *
 * @author Kanishk Upadhyay
 * @version 11.0
 */

 class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class Inventory {

    private Map<String, Integer> rooms = new HashMap<>();

    public Inventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 2);
    }

    public boolean allocate(String roomType) {
        int count = rooms.getOrDefault(roomType, 0);

        if (count > 0) {
            rooms.put(roomType, count - 1);
            return true;
        }

        return false;
    }

    public void printInventory() {
        System.out.println("Remaining Inventory: " + rooms);
    }
}

 class AllocationService {

    public void allocateRoom(Reservation reservation, Inventory inventory) {

        boolean allocated = inventory.allocate(reservation.getRoomType());

        if (allocated) {
            System.out.println(
                    "Room allocated to " + reservation.getGuestName() +
                            " (" + reservation.getRoomType() + ")"
            );
        } else {
            System.out.println(
                    "No rooms available for " + reservation.getGuestName()
            );
        }
    }
}


 class ConcurrentBookingProcessor implements Runnable {

    private Queue<Reservation> bookingQueue;
    private Inventory inventory;
    private AllocationService allocationService;

    public ConcurrentBookingProcessor(
            Queue<Reservation> bookingQueue,
            Inventory inventory,
            AllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            // Critical Section 1: Access booking queue safely
            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    return;
                }

                reservation = bookingQueue.poll();
            }

            // Critical Section 2: Update inventory safely
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}



public class Book_My_Stay_App {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();
        Inventory inventory = new Inventory();
        AllocationService allocationService = new AllocationService();

        // Simulated guest bookings
        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Single"));
        bookingQueue.add(new Reservation("Charlie", "Double"));
        bookingQueue.add(new Reservation("David", "Double"));
        bookingQueue.add(new Reservation("Eva", "Single"));

        // Create booking processor threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService));

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService));

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.printInventory();
    }
}