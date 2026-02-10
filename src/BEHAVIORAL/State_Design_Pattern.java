package BEHAVIORAL;

public class State_Design_Pattern {

    public static void main(String[] args) {

        // Context object
        // It represents the main entity whose behaviour changes by state
        RideContext ride = new RideContext();

        // Initial state
        System.out.println(ride.getStatus());

        // Move to next state
        ride.next();
        System.out.println(ride.getStatus());

        // Move to next state
        ride.next();
        System.out.println(ride.getStatus());

        // Move to next state
        ride.next();
        System.out.println(ride.getStatus());
    }
}


/*
 * State interface
 * Declares behaviour that depends on the current state.
 */
interface RideState {

    // Handles transition to the next state
    void next(RideContext context);

    // Returns the name of the current state
    String getStatus();
}


/*
 * Concrete State - REQUESTED
 */
class RequestedState implements RideState {

    /*
     * Decides what should be the next state
     * when the ride is currently in REQUESTED state
     */
    @Override
    public void next(RideContext context) {

        // State transition is controlled by the state itself
        context.setState(new AcceptedState());
    }

    @Override
    public String getStatus() {
        return "REQUESTED";
    }
}


/*
 * Concrete State - ACCEPTED
 */
class AcceptedState implements RideState {

    /*
     * Behaviour of next() when ride is in ACCEPTED state
     */
    @Override
    public void next(RideContext context) {

        // Move ride to IN_PROGRESS state
        context.setState(new InProgressState());
    }

    @Override
    public String getStatus() {
        return "ACCEPTED";
    }
}


/*
 * Concrete State - IN_PROGRESS
 */
class InProgressState implements RideState {

    /*
     * Behaviour of next() when ride is in IN_PROGRESS state
     */
    @Override
    public void next(RideContext context) {

        // Move ride to COMPLETED state
        context.setState(new CompletedState());
    }

    @Override
    public String getStatus() {
        return "IN_PROGRESS";
    }
}


/*
 * Concrete State - COMPLETED
 */
class CompletedState implements RideState {

    /*
     * Behaviour of next() when ride is already completed
     */
    @Override
    public void next(RideContext context) {

        // No further state transition possible
        System.out.println("Ride already completed. No next state.");
    }

    @Override
    public String getStatus() {
        return "COMPLETED";
    }
}


/*
 * Context
 * Holds the current state and delegates behaviour to it.
 */
class RideContext {

    // Current state of the ride
    private RideState currentState;

    // Initial state is REQUESTED
    public RideContext() {
        this.currentState = new RequestedState();
    }

    /*
     * Allows states to change the current state of the context
     */
    public void setState(RideState rideState) {
        this.currentState = rideState;
    }

    /*
     * Context does not know which state comes next.
     * It delegates the responsibility to the current state.
     */
    public void next() {
        currentState.next(this);
    }

    /*
     * Delegates state-specific information to the current state
     */
    public String getStatus() {
        return currentState.getStatus();
    }
}

