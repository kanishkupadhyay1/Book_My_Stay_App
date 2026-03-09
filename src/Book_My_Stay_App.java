/**
 * Abstract class Room




 * Use Case 2: Basic Room Types & Static Availability
 *
 *  Description:
 * This abstract class represents a generic hotel room.
 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.
 * Inventory-related concerns are intentionally excluded.
 *
 * @author Kanishk Upadhyay
 * @version 2.0
 */
abstract class Room{
    protected int NoOfBeds;
    protected int SquareFeet;
    protected double PricePerNight;
    int Available=5;

    public Room(int NoOfBeds,int SquareFeet,double PricePerNight){
        this.NoOfBeds=NoOfBeds;
        this.SquareFeet=SquareFeet;
        this.PricePerNight=PricePerNight;
    }

    public void DisplayRoomDetails(){
        System.out.println("No of beds: "+NoOfBeds+"\nRoom size: "+SquareFeet+"SqFeet\nRent per Night: $"+PricePerNight+"\nAvailable : "+Available);
    }
}


//double room class
class DoubleRoom extends Room{
    int Available=3;
    public DoubleRoom( ){
        super(2,400,2500);
    }
    @Override
    public void DisplayRoomDetails(){
        System.out.println("Double Room:\nNo of beds: "+NoOfBeds+"\nRoom size: "+SquareFeet+"SqFeet\nRent per Night: $"+PricePerNight+"\nAvailable : "+Available);
    }
}

//Single room class
class SingleRoom extends Room{
    int Available=3;
    public SingleRoom(){
        super(1,250,1500);
    }
    @Override
    public void DisplayRoomDetails(){
        System.out.println("Single Room:\nNo of beds: "+NoOfBeds+"\nRoom size: "+SquareFeet+"SqFeet\nRent per Night: $"+PricePerNight+"\nAvailable : "+Available);
    }
}

//Suiteroom class
class SuiteRoom extends Room{
    int Available=2;

    public SuiteRoom(){
        super(3,750,5000);
    }
    @Override
    public void DisplayRoomDetails(){
        System.out.println("Suite Room:\nNo of beds: "+NoOfBeds+"\nRoom size: "+SquareFeet+"SqFeet\nRent per Night: $"+PricePerNight+"\nAvailable : "+Available);
    }
}


public class Book_My_Stay_App {
    public static void main(String args[]){
        System.out.println("Welcome To Hotel Booking Management System\nSystem  Initialized Successfully.\nVersion: 2.0");

        SingleRoom r1=new SingleRoom(); //object for single room
        r1.DisplayRoomDetails();
        System.out.println();

        DoubleRoom r2=new DoubleRoom();  //object for Double room
        r2.DisplayRoomDetails();
        System.out.println();

        SuiteRoom r3=new SuiteRoom(); //object for Suite room
        r3.DisplayRoomDetails();
        System.out.println();

    }
}
