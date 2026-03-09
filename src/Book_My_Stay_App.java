import java.util.*;

/**
 * Class BookingRequestQueue
 *
 *Use Case 6: Reservation Confirmation & Room Allocation

 *
 * @author Kanishk Upadhyay
 * @version 6.0
 */
class Reservation{
    String name;
    String RoomType;

    public Reservation(String name,String RoomType){
        this.name=name;
        this.RoomType=RoomType;
    }

    public String GetGuestName(){
        return name;
    }

    public String GetRoomType(){
        return RoomType;
    }
}

class BookingRequestQueue{
    private Queue<Reservation>  Request;

    public BookingRequestQueue(){
        Request=new LinkedList<>();
    }

    public void addRequest(Reservation reservation){
        Request.offer(reservation);
    }

    public Reservation getNextRequest(){
        return Request.poll();
    }

    public boolean hasPendingRequest(){
        return !Request.isEmpty();
    }

}

class roomInventory{
    private Map<String,Integer> inventory;
    public roomInventory(){
        this.inventory=new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailablility(String roomType){
        return inventory.getOrDefault(roomType,0);
    }

    public void UpdateAvailibility(String RoomType,int count){
        inventory.put(RoomType,count);
    }
}

class RoomAllocationServices{
    private Set<String> allocatedRoomId;
    private Map<String,Set<String>>assignedRoomByType;

    public RoomAllocationServices(){
        allocatedRoomId=new HashSet<>();
        assignedRoomByType=new HashMap<>();
    }
    public void allocateRoom(Reservation reservation, roomInventory inventory) {

        String roomType = reservation.GetRoomType();

        int available = inventory.getAvailablility(roomType);

        if (available <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomId.add(roomId);

        assignedRoomByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.UpdateAvailibility(roomType, available - 1);

        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.GetGuestName()
                        + ", Room ID: "
                        + roomId
        );
    }


      //unique room ID

    private String generateRoomId(String roomType) {

        int count = assignedRoomByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType + "-" + count;
    }

}

public class Book_My_Stay_App {

    public static void main(String args[]){

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 6.0\n");

        System.out.println("Room Allocation Processing");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        roomInventory inventory = new roomInventory();

        RoomAllocationServices allocationService =
                new RoomAllocationServices();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        while (bookingQueue.hasPendingRequest()) {

            Reservation reservation = bookingQueue.getNextRequest();

            allocationService.allocateRoom(reservation, inventory);
        }
    }
}