import java.util.*;

/**
 * Class BookingRequestQueue
 *
 Use Case 5: Booking Request (FIFO)
 *
 * Description:
 * This class manages booking requests
 * using a queue to ensure fair allocation.
 *
 * Requests are processed strictly
 * in the order they are received.
 *
 * @author Kanishk Upadhyay
 * @version 5.0
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

public class Book_My_Stay_App {

    public static void main(String args[]){

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 5.0\n");

        System.out.println("Booking Request Queue");

        BookingRequestQueue  bookingQueue=new BookingRequestQueue();

        Reservation r1=new Reservation("kanishk","single");
        Reservation r2=new Reservation("Namish","Double");
        Reservation r3=new Reservation("Samay","Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequest()) {

            Reservation current = bookingQueue.getNextRequest();

            System.out.println("Processing booking for Guest: " + current.GetGuestName() + ", Room Type: " + current.GetRoomType());
        }
    }
}