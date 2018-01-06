package entities;

public class AutomatedSystem {
    private static ParkingLot parkingLot;

    public static void setParkingLot(ParkingLot parkingLot) {
        AutomatedSystem.parkingLot = parkingLot;
    }

    public static ParkingLot getParkingLot() {
        return parkingLot;
    }
}
