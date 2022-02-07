package dcelevators;

//this class handles basic information about a request for an elevator
//I have chosen to do the simple implementation of requests being between two floors at a time
public class Request {

    private final int pickupFloor;

    private final int targetFloor;

    private final Direction direction;

    //This constructor assumes that the actual Direction of the request is in line with the direction given as parameter
    public Request(int pickupFloor,int targetFloor, Direction direction)
    {
        if(pickupFloor<0||pickupFloor>55)
            throw new IllegalArgumentException("Floor must be between 0 and 55 (inclusive)");
        if(targetFloor<0||targetFloor>55)
            throw new IllegalArgumentException("Floor must be between 0 and 55 (inclusive)");
        this.pickupFloor = pickupFloor;
        this.targetFloor= targetFloor;
        this.direction = direction;
    }
    
    public int getPickupFloor()
    {
        return pickupFloor;
    }

    public int getTargetFloor()
    {
        return targetFloor;
    }

    public Direction getDirection()
    {
        return direction;
    }
}

