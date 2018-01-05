import entities.AutomatedSystem;
import entities.ParkingLot;
import entities.Vehicle;

import java.util.HashMap;
import java.util.Map;

import static utils.Constants.*;

public class CommandRunner {

    public static void runCommand(String line) {
        String[] partsOfCommand = line.split(" ");
        if(line.contains(CREATE_PARKING_LOT_COMMAND)){
            createParkingLot(partsOfCommand[1]);
        }
        else if(line.startsWith(PARK_COMMAND)){
            runParkCommand(partsOfCommand[1], partsOfCommand[2]);
        }
        else if(line.startsWith(LEAVE_COMMAND)){
            runLeaveCommand(partsOfCommand[1]);
        }
        else if(line.startsWith(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND)){
            findRegNumForCarWithColour(partsOfCommand[1]);
        }
        else if(line.startsWith(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND)){
            findSlotNumberForCarWithColor(partsOfCommand[1]);
        }
        else if(line.startsWith(SLOT_NUMBER_FOR_REGISTRATION_NUMBER_COMMAND)){
            findSlotNumberForRegNum(partsOfCommand[1]);
        }
        else  if(line.startsWith(STATUS)){
            status();
        }
    }

    private static void status(){
        HashMap<Integer, Vehicle> map =
                AutomatedSystem.getParkingLot().getOccupiedParkingSpots();

        System.out.format("%4s%32s%32s", "id" , "Registration Id", "Colour"+ "\n");

        for(Map.Entry<Integer, Vehicle> entry: map.entrySet()){
            System.out.format("%4s%32s%32s",
                    entry.getKey(),
                    entry.getValue().getRegistrationId(),
                    entry.getValue().getColor() + "\n");
        }



    }

    private static void findSlotNumberForRegNum(String regNum){

    }

    private static void findSlotNumberForCarWithColor(String colour){

    }

    private static void findRegNumForCarWithColour(String colour){

    }

    private static void createParkingLot(String num) {
        AutomatedSystem.setParkingLot(new ParkingLot(Integer.valueOf(num)));
        System.out.print("Jai Sai Ram ");
    }

    private static void runLeaveCommand(String num){
        AutomatedSystem.getParkingLot().vacate(Integer.valueOf(num));
        System.out.println("Slot number " + num + " is free");
    }

    private static void runParkCommand(String regNum, String colour){
        Vehicle vehicle = new Vehicle(regNum, colour);
        int spotNo = AutomatedSystem.getParkingLot().park(vehicle);
        if(spotNo == -1){
            System.out.println("Sorry, parking lot is full");
        }
        else{
            System.out.println("Allocated spot number : " + spotNo);
        }
    }
}
