package CruiseProj.Data;

import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
    private final String name;
    private Cabin cabin = null; // null when instantiated, will be assigned later
    private Ship ship = null; // same as above
    private ArrayList<Excursion> excursions;

    public Passenger(String name){
        this.name = name;
        this.excursions = new ArrayList<Excursion>(); // adds empty excursion arrayList
    }
    

    public void addCabin(Cabin cabin){ // assigns passenger to cabin, and if already has one override it
        this.cabin = cabin;
    }

    public String getName(){
        return this.name;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);

        return sb.toString();
    }

    public Cabin getCabin(){
        return this.cabin;
    }

    public void addExcursion(Excursion e){
        excursions.add(e);
    }

    public void removeExcursion(Excursion e){
        excursions.remove(e);
    }

    public ArrayList<Excursion> getExcursions(){
        return this.excursions;
    }

    public void addShip(Ship ship){
        this.ship = ship;
    }

    public Ship getShip(){
        return this.ship;
    }

    public void removeCabin(){
        this.cabin = null;
    }
   

}

