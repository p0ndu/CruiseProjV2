package CruiseProj.Data;

import java.util.ArrayList;

public class Ship {

    private final String name;
    private ArrayList<Excursion> excursions = new ArrayList<>();
    private StandardCabin[] standardCabins;
    private Suite[] suites;
    private final int maxPassengers;
    Passenger[] passengers;

    public Ship(String name, int standardCabins, int suites) { // constructor without max passengers
        this.name = name;
        this.standardCabins = new StandardCabin[standardCabins];
        this.suites = new Suite[suites];

        this.maxPassengers = this.standardCabins.length * 6 + this.suites.length * 4;
        passengers = new Passenger[maxPassengers];
    }

    // -- getters -- 
    public int getMaxPassengers() { // returns max passengers
        return this.maxPassengers;
    }

    public int getNumSuites() { // returns number of suites
        return this.suites.length;
    }

    public int getNumStandardCabins() { // returns number of standard cabins
        return this.standardCabins.length;
    }

    public void addSuite(Suite suite) { // adds suite to ship
        boolean added = false;
        for (int i = 0; i < suites.length; i++) {
            if (suites[i] == null) {
                suites[i] = suite;
                i = suites.length;
                added = true;
            }
        }
        if (!added) {
            System.out.println("Suites are full");
        }
    }

    public StandardCabin getStandardCabin(int index) { // returns standard cabin at index
        return this.standardCabins[index];
    }

    public Suite getSuite(int index) { // returns suite at index
        return this.suites[index];
    }

    public StandardCabin[] getStandardCabins() { // returns standard cabins
        return this.standardCabins;
    }

    public Suite[] getSuites() { // returns suites
        return this.suites;
    }

    public String getName() { // returns name of ship
        return this.name;
    }

    public Passenger[] getPassengers() { // returns passengers
        return this.passengers;
    }

    public int getNumPassengers(){
        int count = 0;
        for(int i = 0; i < passengers.length; i++){
            if(passengers[i] != null){
                count++;
            }
        }

        return count;
    }

    public Excursion[] getExcursions(){
        Excursion[] outArray = excursions.toArray(new Excursion[0]); //converted to excursion array for output, easier for other functions to use than an arrayList
        return outArray;
    }
    // -- setters --

    public void addPassenger(Passenger passIn){ // adds passenger to passengers if there is space
        boolean added = false;
        for (int i = 0; i < passengers.length; i++){ // tries to add p1 to passengers
            if (passengers[i] == null){
                passengers[i] = passIn;
                i = passengers.length;
                added = true;
                sortPassengers();
            }
        }

        if (!added) {
            System.out.println("#### Cabin is full, passenger not added ####");
        }
    }

    public void addStandardCabin(StandardCabin standardCabin) { // adds standard cabin to ship
        boolean added = false;
        for (int i = 0; i < standardCabins.length; i++) {
            if (standardCabins[i] == null) {
                standardCabins[i] = standardCabin;
                i = standardCabins.length;
                added = true;
            }
        }
        if (!added) {
            System.out.println("Standard cabins are full");
        }
    }

    public void addExcursion(Excursion e){
        boolean alreadyAdded = false;
        for (int i = 0; i < excursions.size(); i++){
            if (excursions.get(i).equals(e)){
                alreadyAdded = true;
                i = excursions.size();
            }
        }
        if (!alreadyAdded){
            excursions.add(e);
        }
        else{
            System.out.println("error: excursion already added to ship");
        }
    }

    private void sortPassengers(){ // sorts passengers alphabetically using a bubble sort, dont care about efficiency rn
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
