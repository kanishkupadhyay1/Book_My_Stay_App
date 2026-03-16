import java.util.*;
import java.io.*;
/**
 * Class Add on service
 *
 *Use Case 12: Data Persistence & System Recovery

 *
 * @author Kanishk Upadhyay
 * @version 12.0
 */

 class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public void setRoomCount(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getRoomCount(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }

    public void printInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

 class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     * Format:
     * roomType=availableCount
     */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {

                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.setRoomCount(roomType, count);
                }
            }

            System.out.println("Inventory restored from file.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory. Starting fresh.");
        }
    }
}





public class Book_My_Stay_App {

    public static void main(String[] args) {

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        System.out.println("System Recovery");

        // Load saved inventory
        persistenceService.loadInventory(inventory, filePath);

        // If no data loaded, initialize default inventory
        if (inventory.getAllRooms().isEmpty()) {

            inventory.setRoomCount("Single", 5);
            inventory.setRoomCount("Double", 3);
            inventory.setRoomCount("Suite", 2);
        }

        inventory.printInventory();

        // Save snapshot before shutdown
        persistenceService.saveInventory(inventory, filePath);
    }
}