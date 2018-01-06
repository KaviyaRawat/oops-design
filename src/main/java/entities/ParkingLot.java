package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ParkingLot {

    private HashMap<Integer, Vehicle> occupiedParkingSpots = new HashMap();
    private int parkingSpotsCount;
    private PriorityQueue<Integer> nearestSpaceQueue = new PriorityQueue<Integer>();
    private HashMap<String, List<Integer>> colourToSpotMap = new HashMap();
    private HashMap<String, Integer> regNoToSpotMap = new HashMap();

    public HashMap<Integer, Vehicle> getOccupiedParkingSpots() {
        return occupiedParkingSpots;
    }

    public ParkingLot(int parkingSpotsCount) {
        this.parkingSpotsCount = parkingSpotsCount;
        for(int i = 1; i<= parkingSpotsCount; i++){
            nearestSpaceQueue.add(i);
        }
    }

    public int getParkingSpotsCount() {
        return parkingSpotsCount;
    }

    public void setParkingSpotsCount(int parkingSpotsCount) {
        this.parkingSpotsCount = parkingSpotsCount;
    }

    public void vacate(Integer spotNo){
        nearestSpaceQueue.add(spotNo);
        Vehicle car = occupiedParkingSpots.get(spotNo);
        colourToSpotMap.get(car.getColor()).remove(spotNo);
        regNoToSpotMap.remove(car.getRegistrationId());
        occupiedParkingSpots.remove(spotNo);
    }

    public int park(Vehicle vehicle){
        if(occupiedParkingSpots.size() == parkingSpotsCount){
            return -1;
        }
        int spotNo = nearestSpaceQueue.poll();
        occupiedParkingSpots.put(spotNo, vehicle);

        if(colourToSpotMap.get(vehicle.getColor())==null){
            colourToSpotMap.put(vehicle.getColor(), new ArrayList<Integer>());
        }
        colourToSpotMap.get(vehicle.getColor()).add(spotNo);

        regNoToSpotMap.put(vehicle.getRegistrationId(), spotNo);

        return spotNo;
    }

}
