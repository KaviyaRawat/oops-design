import entities.AutomatedSystem;
import entities.ParkingLot;
import entities.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

public class CommandRunner {

    public static void runCommand(String line) {
        String[] partsOfCommand = line.split(" ");
        if(partsOfCommand.length==0)
            return;
        if(partsOfCommand.length>=2) {
            if (line.contains(CREATE_PARKING_LOT_COMMAND)) {
                createParkingLot(partsOfCommand[1]);
            } else if (AutomatedSystem.getParkingLot() != null && AutomatedSystem.getParkingLot().getParkingSpotsCount() > 0) {
                if (line.startsWith(PARK_COMMAND)) {
                    if (partsOfCommand.length == 3)
                        runParkCommand(partsOfCommand[1], partsOfCommand[2]);
                    else
                        System.out.println("Incomplete Command");
                } else if (line.startsWith(LEAVE_COMMAND)) {
                    runLeaveCommand(partsOfCommand[1]);
                } else if (line.startsWith(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND)) {
                    findRegNumForCarWithColour(partsOfCommand[1]);
                } else if (line.startsWith(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_COMMAND)) {
                    findSlotNumberForCarWithColor(partsOfCommand[1]);
                } else if (line.startsWith(SLOT_NUMBER_FOR_REGISTRATION_NUMBER_COMMAND)) {
                    findSlotNumberForRegNum(partsOfCommand[1]);
                }
            } else {
                System.out.println("Parking Lot not yet created");
            }
        }
        else{
            if (line.startsWith(STATUS)) {
                status();
            }
            else {
                System.out.println("Incomplete Command");
            }
        }
    }

    private static void status() {
        HashMap<Integer, Vehicle> map =
                AutomatedSystem.getParkingLot().getOccupiedParkingSpots();

        // Better Formatter : Example shows tab delimited so the below one used.
        /*System.out.format("%4s%32s%32s", "id" , "Registration Id", "Colour"+ "\n");

        for(Map.Entry<Integer, Vehicle> entry: map.entrySet()){
            System.out.format("%4s%32s%32s",
                    entry.getKey(),
                    entry.getValue().getRegistrationId(),
                    entry.getValue().getColor() + "\n");
        }*/

        System.out.format("Slot No.\tRegistration No\tColour" + "\n");

        for (Map.Entry<Integer, Vehicle> entry : map.entrySet()) {
            System.out.println(
                    entry.getKey() + "\t" +
                            entry.getValue().getRegistrationId() + "\t" +
                            entry.getValue().getColor());
        }

    }

    private static void findSlotNumberForRegNum(String regNum) {
        Integer num =
                AutomatedSystem
                .getParkingLot()
                .getRegNoToSpotMap()
                .get(regNum);
        if(num == null){
            System.out.println("Not found");
        }
        else {
            System.out.println(
                    AutomatedSystem
                            .getParkingLot()
                            .getRegNoToSpotMap()
                            .get(regNum));
        }
    }

    private static void findSlotNumberForCarWithColor(String colour) {
        List<Integer> spotNos = AutomatedSystem
                .getParkingLot().getColourToSpotMap().get(colour);
        if(spotNos.size()==0){
            System.out.println("Not found");
        }
        else {
            System.out.println(spotNos.toString().replaceAll("[\\[\\]]",""));
        }
    }

    private static void findRegNumForCarWithColour(String colour) {

        List<Integer> spotNos = AutomatedSystem
                .getParkingLot().getColourToSpotMap().get(colour);
        if(spotNos.size()==0){
            System.out.println("Not found");
        }
        else {
            List<String> regNums = new ArrayList<>();
            spotNos.forEach(spotNo -> {
                regNums.add(AutomatedSystem
                        .getParkingLot().getOccupiedParkingSpots()
                        .get(spotNo).getRegistrationId());
            });
            System.out.println(
                    String.join(", ", regNums));
        }

    }

    private static void createParkingLot(String num) {
        AutomatedSystem.setParkingLot(new ParkingLot(Integer.valueOf(num)));
        System.out.println("Created a parking lot with " + num + " slots");
    }

    private static void runLeaveCommand(String num) {
        AutomatedSystem.getParkingLot().vacate(Integer.valueOf(num));
        System.out.println("Slot number " + num + " is free");
    }

    private static void runParkCommand(String regNum, String colour) {
        Vehicle vehicle = new Vehicle(regNum, colour);
        int spotNo = AutomatedSystem.getParkingLot().park(vehicle);
        if (spotNo == -1) {
            System.out.println("Sorry, parking lot is full");
        } else {
            System.out.println("Allocated slot number: " + spotNo);
        }
    }
}
