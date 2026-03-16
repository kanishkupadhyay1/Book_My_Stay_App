import java.util.*;
/**
 * Class Add on service
 *
 *Use Case 8: Booking History & Reporting

 *
 * @author Kanishk Upadhyay
 * @version 8.0
 */

class Reservation{
   private String name;
     private String roomType;

    public Reservation(String name,String roomType){
        this.roomType=roomType;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getRoomType(){
        return roomType;
    }

    public void getDetails(){
        System.out.println("Guest: "+getName()+", "+getRoomType());
    }
}

class BookingHistory{
    private List<Reservation> confirmedReservations;

    public BookingHistory(){
        confirmedReservations=new ArrayList<>();
    }
    public void AddReservation(Reservation reservation){
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations(){
        return confirmedReservations;
    }
}

class BookingReportService{

    public void generateReport(BookingHistory history){
        System.out.println("Booking history: ");
        for(Reservation  r: history.getConfirmedReservations()){
            System.out.println();
            r.getDetails();

        }
    }
}



public class Book_My_Stay_App {

    public static void main(String args[]){

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 8.0\n");

        BookingHistory b1=new BookingHistory();
        b1.AddReservation(new Reservation("namish","single"));
        b1.AddReservation(new Reservation("manas","double"));

        b1.AddReservation(new Reservation("samay","double"));

        BookingReportService  service=new BookingReportService();
        service.generateReport(b1);


    }
}