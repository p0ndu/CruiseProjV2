package CruiseProj.Data;

public class Excursion {

    private final Port destination;
    private final DayOfWeek day;
    private final int maxPassengers;
    private Passenger[] passengers;
    private int currentPassengers = 0;

    public Excursion(Port destination, DayOfWeek day, int maxPass) {
        this.destination = destination;
        this.day = day;
        this.maxPassengers = maxPass;
        passengers = new Passenger[maxPass];
    }

    // -- getters -- 
    public DayOfWeek getDay() {
        return this.day;
    }

    public int getMaxPassengers() {
        return this.maxPassengers;
    }

    public Port getDestination() {
        return this.destination;
    }

    public int getCurrentPassengers() {
        return this.currentPassengers;
    }

    public Passenger getPassenger(int index) {
        return this.passengers[index];
    }

    public Passenger[] getPassengers() {
        return this.passengers;
    }

    public void addPassenger(Passenger passIn){ // adds passenger if there is space
        boolean added = false;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = passIn;
                added = true;
                i = passengers.length;
                currentPassengers += 1;
            }
        }

        if (!added) {
            System.out.println("#### Excursion is full, passenger not added ####");
        }
    }

    // fix things requiring passenger util
    // -- misc -- 
    // public void addPassenger(Passenger passenger) { // adds passenger onto the first null space found
    //     if (currentPassengers < maxPassengers) {
    //         this.passengers = PassengerUtil.addPassenger(passengers, passenger);
    //         currentPassengers += 1;
    //     } else {
    //         System.out.println("Excursion is full");
    //     }
    // }

    // public void removePassenger(Passenger pass) { // searches for an removes passed passenger
    //     this.passengers = PassengerUtil.removePassenger(passengers, pass);
    //     currentPassengers = - 1;
    // }

    public boolean hasSpace() {
        return currentPassengers < maxPassengers; // returns if currentPassengers is smaller than maxPassengers, i.e. if it has space to add another
    }

}
