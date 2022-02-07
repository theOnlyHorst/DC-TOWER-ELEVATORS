package dcelevators;


//The Elevator class contains basic information for each Elevator such as it's current floor and it the current request it is doing, it also has basic movement such as moving up or down
public class Elevator {

    //Current Floor Stored as int
    private int currentFloor;

    //Stores the currentRequest of the Elevator
    private Request currentRequest;

    //A state variable to store if the elevator has visited the pickup floor of the current request yet
    private boolean hasPickedUp;

    //Reference to the elevator manager that controls this elevator, used to call the requestCompleted Method of the ElevatorManager
    private final ElevatorManager elevatorManager;

    public Elevator(int currentFloor,ElevatorManager elevatorManager)
    {
        if(currentFloor<0||currentFloor>55)
            throw new IllegalArgumentException("Floor must be between 0 and 55 (inclusive)");
        this.currentFloor = currentFloor;
        this.elevatorManager = elevatorManager;
    }

    public void setRequest(Request request)
    {
       currentRequest=request;
    }

    public boolean hasRequest()
    {
       if (currentRequest==null)
           return false;
       else
           return true;
    }

    public Request getCurrentRequest()
    {
       return currentRequest;
    }
    public int getCurrentFloor()
    {
        return currentFloor;
    }

    //this method simulates the elevator moving one floor and all the logic required to complete Requests
    //one Simulation call either is:
    //The elevator idling when there is no request
    //The elevator moving towards either the pickup or target floor by one floor
    //The elevator changing from pickup floor to target floor and waiting during that simulate call
    //The elevator completing the request and waiting during that simulate call
    public void simulate()
    {
        if(currentRequest==null)
        {
            return;
        }
        if(!hasPickedUp)
        {
            if(currentFloor== currentRequest.getPickupFloor())
            {
                hasPickedUp=true;
            }
            else {
                if(currentFloor<currentRequest.getPickupFloor())
                    currentFloor++;
                else
                    currentFloor--;
            }
        }
        else
        {
            if(currentFloor==currentRequest.getTargetFloor())
            {
                hasPickedUp = false;
                currentRequest = null;
                elevatorManager.requestCompleted(this);
            }
            else
            {
                if(currentRequest.getDirection()==Direction.UP)
                    currentFloor++;
                else
                    currentFloor--;
            }
        }
    }
}
