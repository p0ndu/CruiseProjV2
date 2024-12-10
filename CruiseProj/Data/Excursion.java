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
                sortPassengers();
            }
        }

        if (!added) {
            System.out.println("#### Excursion is full, passenger not added ####");
        }
    }

   // misc

    public boolean hasSpace() {
        return currentPassengers < maxPassengers; // returns if currentPassengers is smaller than maxPassengers, i.e. if it has space to add another
    }

    private void sortPassengers(){ // sorts passengers alphabetically with a bubble sort, not efficient at all but rn i dont really care
        for (int i = 0; i < passengers.length; i++) {
            for (int j = i + 1; j < passengers.length; j++) {
                if (passengers[i] != null && passengers[j] != null) {
                    if (passengers[i].getName().compareTo(passengers[j].getName()) > 0) {
                        Passenger temp = passengers[i];
                        passengers[i] = passengers[j];
                        passengers[j] = temp;
                    }
                }
            }
        }
    }

}
