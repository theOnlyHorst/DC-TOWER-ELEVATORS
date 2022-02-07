package dcelevators;

//Class to test the program
public class Start {

    public static void main(String[] args)
    {
        ElevatorManager manager = new ElevatorManager();
        System.out.println("Test 1: Basic Elevator Status");
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 2: Adding Requests");
        manager.addRequest(0,35,Direction.UP);
        manager.addRequest(0,45,Direction.UP);
        manager.addRequest(27,0,Direction.DOWN);
        manager.addRequest(11,0,Direction.DOWN);
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 3: Simulating twice (two of the elevators should have moved by two floors and two others should have moved by one floor");
        manager.simulate();
        manager.simulate();
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 4: Simulating until elevator #3 reaches the pickup floor and checking if it moves down again");
        for (int i=0;i<9;i++)
        {
            manager.simulate();
        }
        manager.simulate();
        //at this point it should stay on floor 11
        manager.checkElevatorStatus();
        manager.simulate();
        //at this point it should have moved down by one
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 5: Simulating until elevator #3 reaches target floor and checking if elevator completes request");
        for(int i=0;i<10;i++)
        {
            manager.simulate();
        }
        //at this point the elevator should've reached the target floor but not completed the request yet
        manager.checkElevatorStatus();
        manager.simulate();
        //now elevator #3 should have no request
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 6: Simulating until elevator #0 has reached targetFloor (35) and completes request");
        for(int i=0;i<13;i++)
        {
            manager.simulate();
        }
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 7: Adding a request from floor 2 so that it picks elevator #3 because it is closer to floor 2");
        manager.addRequest(2,0,Direction.DOWN);
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 8: Adding a request from floor 39 so that it picks elevator #0 because it is closer to floor 39");
        manager.addRequest(39,0,Direction.DOWN);
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 9: Adding 5 more Requests so that they will populate the Request queue");
        manager.addRequest(0,48,Direction.UP);
        manager.addRequest(0,53,Direction.UP);
        manager.addRequest(0,10,Direction.UP);
        manager.addRequest(15,0,Direction.DOWN);
        manager.addRequest(29,0,Direction.DOWN);
        manager.checkElevatorStatus();
        System.out.println();
        System.out.println("Test 10: Simulating until elevator #3 has completed it's request and received the next Request from the Queue");
        for(int i=0;i<6;i++)
        {
            manager.simulate();
        }
        manager.checkElevatorStatus();
        System.out.println("Test 11: Simulating until elevator #1 and #6 have completed their requests and #1 received the next request from the queue and #6 is idle");
        for(int i=0;i<6;i++)
        {
            manager.simulate();
        }
        manager.checkElevatorStatus();
    }
}
