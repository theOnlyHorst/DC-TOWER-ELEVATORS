package dcelevators;


import java.util.LinkedList;

//this is the main class of the program
//takes in new requests and picks the closest elevator it can find to assign the request to, is no elevator free it will queue the requests
public class ElevatorManager {

    //This is the list of elevators
    private final LinkedList<Elevator> elevators = new LinkedList<Elevator>();
    //This is the Queue of Requests that haven't been assigned yet
    //LinkedList is used here for the convenience of it acting as list and as queue
    private final LinkedList<Request> requestQueue = new LinkedList<>();

    //This constructor creates 7 elevators all starting at floor 0, this could be changed to have the amount of elevators as parameter
    public ElevatorManager()
    {
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
        elevators.add(new Elevator(0,this));
    }
    //Called when someone adds a Request by pressing the button or swiping their employee card
    //this method will assign the request to the closest available elevator
    //is no elevator available it will queue the Request in the requestQueue
    public void addRequest(int currentFloor, int targetFloor, Direction direction)
    {
        Elevator pickedElevator = null;
        for(Elevator e:elevators)
        {
            //checking if elevator is available
            if(!e.hasRequest())
            {
                if(pickedElevator==null)
                {
                    pickedElevator = e;
                }
                else
                {
                    //Comparing which elevator is closer by comparing the absolute difference between them
                    if(Math.abs(pickedElevator.getCurrentFloor()-currentFloor)>Math.abs(e.getCurrentFloor()-currentFloor))
                    {
                        pickedElevator = e;
                    }
                }

            }
        }
        if(pickedElevator==null)
        {
            requestQueue.offer(new Request(currentFloor,targetFloor,direction));
        }
        else {
            pickedElevator.setRequest(new Request(currentFloor,targetFloor,direction));
        }
    }
    //Called when an elevator completes a request, used to add queued requests to elevators that are free
    public void requestCompleted(Elevator e)
    {
        if(!requestQueue.isEmpty())
        {
            e.setRequest(requestQueue.poll());
        }
    }
    //calls the simulate methods of all elevators, this would be the main loop in an asynchronous implementation
    public void simulate()
    {
        for(Elevator e:elevators)
        {
            e.simulate();
        }
    }

    //Prints the current Status of all Elevators, used to verify test cases
    public void checkElevatorStatus()
    {
        for(int x = 0;x<elevators.size();x++)
        {
            Elevator e = elevators.get(x);
            System.out.println("Elevator #"+x);
            System.out.println("Current Floor: " + e.getCurrentFloor());
            System.out.println("Current Request: "+(e.hasRequest()?"[pickupFloor: "+ e.getCurrentRequest().getPickupFloor()+ " targetFloor: " + e.getCurrentRequest().getTargetFloor() + " direction: "+e.getCurrentRequest().getDirection()+"]" :"none"));
            System.out.println("---------------------------------------------------------");
        }
        System.out.println("Request Queue: ");
        for (Request r :requestQueue) {
            System.out.println("[pickupFloor: "+ r.getPickupFloor()+ " targetFloor: " + r.getTargetFloor() + " direction: "+r.getDirection()+"]");
        }
        System.out.println("########################################################################");
    }



}
