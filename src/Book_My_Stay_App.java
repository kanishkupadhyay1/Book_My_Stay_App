import java.util.*;

/**
 * Class RoomsearchService
 *
 Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class demonstrates how guests
 * can view available rooms without
 * modifying inventory data.
 *
 * The system enforces read-only access
 * by design and usage discipline.
 *
 * @author Kanishk Upadhyay
 * @version 4.0
 */
class RoomsearchService{
    private RoomInventory inventory;

    public RoomsearchService(RoomInventory inventory){
        this.inventory=inventory;
    }

    public void SearchAvailableRooms(){
        System.out.println("\nAvailable Rooms:\n");

        Map<String,Integer> rooms= inventory.getInventory();

        for (Map.Entry<String, Integer> entry : rooms.entrySet()) {

            String roomType = entry.getKey();
            int available = entry.getValue();

            if (available > 0) {

                Room room = null;

                switch (roomType) {
                    case "SingleRoom":
                        room = new SingleRoom();
                        break;

                    case "DoubleRoom":
                        room = new DoubleRoom();
                        break;

                    case "SuiteRoom":
                        room = new SuiteRoom();
                        break;
                }

                if (room != null) {
                    room.DisplayRoomDetails();
                    System.out.println("Available: " + available);
                    System.out.println("---------------------------");
                }
            }
        }
    }
}

class RoomInventory{
    Map<String,Integer> inventory=new HashMap<>();

    public RoomInventory(){
        inventory.put("SingleRoom",3);
        inventory.put("DoubleRoom",3);
        inventory.put("SuiteRoom",2);
    }

    public void DisplayInventory(){
        for(Map.Entry<String,Integer> entry: inventory.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    public int GetAvailablity(String roomType){ //get the availablity based on room type
        return inventory.get(roomType);
    }

    public void UpdateAvailability(String roomType,int count){  //updating the avaiblity
        inventory.put(roomType,count);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

abstract class Room{
    protected int NoOfBeds;
    protected int SquareFeet;
    protected double PricePerNight;


    public Room(int NoOfBeds,int SquareFeet,double PricePerNight){
        this.NoOfBeds = NoOfBeds;
        this.SquareFeet = SquareFeet;
        this.PricePerNight = PricePerNight;
    }

    public void DisplayRoomDetails(){
        System.out.println("No of beds: "+NoOfBeds+
                "\nRoom size: "+SquareFeet+" SqFeet"+
                "\nRent per Night: ₹"+PricePerNight);
    }
}


// Double Room
class DoubleRoom extends Room{


    public DoubleRoom(){
        super(2,400,2500);
    }

    @Override
    public void DisplayRoomDetails(){
        System.out.println("Double Room:\nNo of beds: "+NoOfBeds+
                "\nRoom size: "+SquareFeet+" SqFeet"+
                "\nRent per Night: ₹"+PricePerNight);
    }
}


// Single Room
class SingleRoom extends Room{


    public SingleRoom(){
        super(1,250,1500);
    }

    @Override
    public void DisplayRoomDetails(){
        System.out.println("Single Room:\nNo of beds: "+NoOfBeds+
                "\nRoom size: "+SquareFeet+" SqFeet"+
                "\nRent per Night: ₹"+PricePerNight);
    }
}


// Suite Room
class SuiteRoom extends Room{


    public SuiteRoom(){
        super(3,750,5000);
    }

    @Override
    public void DisplayRoomDetails(){
        System.out.println("Suite Room:\nNo of beds: "+NoOfBeds+
                "\nRoom size: "+SquareFeet+" SqFeet"+
                "\nRent per Night: ₹"+PricePerNight);
    }
}


public class Book_My_Stay_App {

    public static void main(String args[]){

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 4.0\n");

        RoomInventory inventory=new RoomInventory();

        RoomsearchService searchService = new RoomsearchService(inventory);

        searchService.SearchAvailableRooms();
    }
}