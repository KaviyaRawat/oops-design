import entities.ParkingLot;
import entities.Vehicle;

public class CommandRunner {
    private static ParkingLot parkingLot;

    public static void runCommand(String line) {
        if(line.contains("create_parking_lot")){
            String[] partsOfCommand = line.split(" ");
            parkingLot = new ParkingLot(Integer.valueOf(partsOfCommand[1]));
        }
        else if(line.startsWith("park")){
            String[] partsOfCommand = line.split(" ");
            Vehicle vehicle = new Vehicle(partsOfCommand[1], partsOfCommand[2]);
            int spotNo = parkingLot.park(vehicle);
            if(spotNo == -1){
                System.out.println("Sorry, parking lot is full");
            }
            else{
                System.out.println("Allocated spot number : " + spotNo);
            }
        }
        else if(line.startsWith("leave")){
            String[] partsOfCommand = line.split(" ");
            parkingLot.vacate(Integer.valueOf(partsOfCommand[1]));
            System.out.println("Slot number " + partsOfCommand[1] + " is free");
        }
    }
}
