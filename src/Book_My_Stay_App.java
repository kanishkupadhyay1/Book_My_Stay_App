import java.util.*;
/**
 * Class Add on service
 *
 *Use Case 7: Add-On Service Selection

 *
 * @author Kanishk Upadhyay
 * @version 7.0
 */

 class AddonService{
    private String ServiceName;
    private double cost;

    public AddonService(String serviceName,double cost){
        this.ServiceName=serviceName;
        this.cost=cost;
    }

    public String getService(){
        return ServiceName;
    }

    public double getCost(){
        return cost;
    }
}

// class add on service manager

class AddOnServiceManager{

     //map to store services with a reservation
    private Map<String,List<AddonService>> ServicesByReservation;

    public AddOnServiceManager(){
        ServicesByReservation=new HashMap<>();
    }

    public void addService(String reservationID, AddonService Service) {

        List<AddonService> addOn = ServicesByReservation.get(reservationID);

        if (addOn == null) {
            addOn = new ArrayList<>();
        }

        addOn.add(Service);
        ServicesByReservation.put(reservationID, addOn);
    }

    public double calculateTotalCost(String reservationID){
        double total=0;
        List<AddonService> addOns=ServicesByReservation.get(reservationID);
        for(AddonService s: addOns){
            total+=s.getCost();
        }
        return total;
    }
}


public class Book_My_Stay_App {

    public static void main(String args[]){

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 7.0\n");

       String resevationId=new String("Single-1");
       AddonService lunch=new AddonService("lunch",1500.20);

       AddOnServiceManager m1=new AddOnServiceManager();
       m1.addService(resevationId,lunch);
       System.out.println("Add-on Service Selection\nReservation ID: "+resevationId+"\nTotal Add-on Cost: "+m1.calculateTotalCost(resevationId));

    }
}