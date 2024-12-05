package CruiseProj.Data;

import CruiseProj.Business.PassengerUtil;

public abstract class Cabin { // abstract class as it wont be instantiated on its own, dont think any abstract methods will be used but this is here just in case

    private int number;
    private Passenger[] passengers;

    public Cabin(int number, int maxPassengers) { // constructor
        this.number = number;
        passengers = new Passenger[maxPassengers];
    }

    // -- getters -- 
    public Passenger getPassenger(int index) {
        return passengers[index];
    }

    public Passenger[] getAllPassengers() {
        return passengers;
    }

    public int getNum() {
        return this.number;
    }

    public int getNumPassengers() {
        int count = 0;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null) {
                count++;
            }
        }

        return count;
    }

    public abstract String getType(); // abstract method to be implemented in subclasses

    public abstract boolean hasSpace(); // abstract method to be implemented in subclasses

    public void addPassenger(Passenger passIn){ // adds passenger if there is space
        boolean added = false;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = passIn;
                added = true;
                i = passengers.length;
            }
        }

        if (!added) {
            System.out.println("#### Cabin is full, passenger not added ####");
        }
    }

    // // fix everything below


    // // -- removers -- 
    // public void removePassenger(int index) { // returns index to null
    //     passengers[index] = null;
    //     PassengerUtil.organisePassengers(passengers);
    // }

    public void removePassenger(Passenger passenger) { // removes passenger from passengers
        passengers = PassengerUtil.removePassenger(this, passenger);
    }

}
