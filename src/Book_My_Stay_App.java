import java.util.*;

/**
 * Class RoomInventory
 Use Case 3: Centralized Room Inventory Management

 *Description:
 *This class demonstrates how room availability is managed using a centralized inventory.
 *Room objects are used to retrieve pricing and room characteristics.
 *No booking or search logic is introduced here.
 * @author Kanishk Upadhyay
 * @version 2.0
 */
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
}

abstract class Room{
    protected int NoOfBeds;
    protected int SquareFeet;
    protected double PricePerNight;
    int Available = 5;

    public Room(int NoOfBeds,int SquareFeet,double PricePerNight){
        this.NoOfBeds = NoOfBeds;
        this.SquareFeet = SquareFeet;
        this.PricePerNight = PricePerNight;
    }

    public void DisplayRoomDetails(){
        System.out.println("No of beds: "+NoOfBeds+
                "\nRoom size: "+SquareFeet+" SqFeet"+
                "\nRent per Night: ₹"+PricePerNight+
                "\nAvailable : "+Available);
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
        System.out.println("Version: 3.0\n");

        RoomInventory inventory=new RoomInventory();
        System.out.println("Current room Inventory");
        inventory.DisplayInventory();

        SingleRoom r1 = new SingleRoom();
        r1.DisplayRoomDetails();
        System.out.println("Availablity: "+inventory.GetAvailablity("SingleRoom"));
        System.out.println();

        DoubleRoom r2 = new DoubleRoom();
        r2.DisplayRoomDetails();
        System.out.println("Availablity: "+inventory.GetAvailablity("DoubleRoom"));
        System.out.println();

        SuiteRoom r3 = new SuiteRoom();
        r3.DisplayRoomDetails();
        System.out.println("Availablity: "+inventory.GetAvailablity("SuiteRoom"));
        System.out.println();

        inventory.UpdateAvailability("SingleRoom",2);

        System.out.println("\nInventory After Booking:");
        inventory.DisplayInventory();
    }
}