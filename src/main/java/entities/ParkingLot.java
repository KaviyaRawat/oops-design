package entities;

import java.util.HashMap;
import java.util.PriorityQueue;

public class ParkingLot {

    private HashMap<Integer, Vehicle> occupiedParkingSpots = new HashMap();
    private int parkingSpotsCount;
    private PriorityQueue<Integer> nearestSpaceQueue = new PriorityQueue<Integer>();

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
        occupiedParkingSpots.remove(spotNo);
    }

    public int park(Vehicle vehicle){
        if(occupiedParkingSpots.size() == parkingSpotsCount){
            return -1;
        }
        int spotNo = nearestSpaceQueue.poll();
        occupiedParkingSpots.put(spotNo, vehicle);
        return spotNo;
    }

}
