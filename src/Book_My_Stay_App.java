import java.util.*;
/**
 * Class Add on service
 *
 *Use Case 9: Error Handling & Validation

 *
 * @author Kanishk Upadhyay
 * @version 9.0
 */


class InvalidBookException extends Exception{
    public InvalidBookException(String message){
        super(message);
    }
}

class RoomInventory {

    private List<String> availableRooms;

    public RoomInventory() {
        availableRooms = new ArrayList<>();
        availableRooms.add("single");
        availableRooms.add("double");
        availableRooms.add("suite");
    }

    public boolean isValidRoom(String type) {
        return availableRooms.contains(type.toLowerCase());
    }
}


class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookException {

        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidBookException("Booking failed: Guest name cannot be empty.");
        }

        if (!inventory.isValidRoom(roomType)) {
            throw new InvalidBookException("Booking failed: Invalid room type selected.");
        }

        System.out.println("Booking validation successful.");
    }
}







public class Book_My_Stay_App {

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        System.out.println("Welcome To Hotel Booking Management System");
        System.out.println("System Initialized Successfully.");
        System.out.println("Version: 9.0\n");

    System.out.println("Booking validation");
    System.out.print("enter guest Name: ");
    String name=sc.next();

    System.out.print("enter Room type: ");
    String type=sc.next();
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {
            validator.validate(name, type, inventory);
            System.out.println("Booking Confirmed for " + name);
        } catch (InvalidBookException e) {
            System.out.println(e.getMessage());
        }

    }
}