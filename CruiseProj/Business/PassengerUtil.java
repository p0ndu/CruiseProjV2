package CruiseProj.Business;

// importing all necessary classes
import CruiseProj.Data.Cabin;
import CruiseProj.Data.Excursion;
import CruiseProj.Data.Passenger;
import CruiseProj.Data.Ship;

public class PassengerUtil { // helper class to allow sorting of passengers across different classes and files
    
    public static Passenger[] organisePassengers(Passenger[] passengers){ // returns sorted and shifted array
        return shiftPassengers(sortPassengers(passengers));
    }


    private static Passenger[] sortPassengers(Passenger[] passengers) { //bubble sort that sorts passengers alphabetically
        boolean loop = true;
        Passenger passBuffer;
        int n = passengers.length; // storing length as variable to avoid extra computation each loop
        do { 
            loop = false;
            for (int i = 1; i  < n; i++){
                if (passengers[i - 1] != null && passengers[i] != null){
                    if (passengers[i-1].getName().compareToIgnoreCase(passengers[i].getName()) > 0){ // if out of order, swap
                        passBuffer = passengers[i -1];
                        passengers[i - 1] = passengers[i];
                        passengers[i] = passBuffer;

                        loop = true; // if something was switched loop again
                    }
                }
            }
             n--; // reduce n after each loop as the nth last elements will be sorted after n loops   
        } while (loop);

        return passengers;
    }

    public static Passenger[] shiftPassengers(Passenger[] passengers) { // bubble sort that shifts passengers up the array
        boolean loop = true;
        int n = passengers.length;
       do {
            loop = false;
            for (int i = 1; i < n; i++) {
                if (passengers[i - 1] == null && passengers[i] != null) { // if one is null and the other isnt swap it
                    passengers[i - 1] = passengers[i];
                    passengers[i] = null;

                    loop = true; // if 2 indexes are switched, loop the array again
                }
            }
            n--; // reduce n after each loop as the nth last elements will be sorted after n loops
        }while(loop);

        return passengers;
    }

    public static Passenger[] addPassenger(Passenger[] pArray, Passenger toAdd){ // helper func to add passenger to array if there is space
        boolean added = false;
        for (int i = 0; i < pArray.length; i++){
            if (pArray[i]!= null && pArray[i].equals(toAdd)){
                System.out.println("Passenger already added");
                i = pArray.length;
            }
            else if (pArray[i] == null){
                pArray[i] = toAdd;
                i = pArray.length;
                added = true;
            }
        }
        if (!added){
            System.out.println("No space for passenger");
        }

        pArray = sortPassengers(pArray);
        return pArray;
    }

    public static Passenger[] removePassenger(Passenger[] pArray, Passenger toRemove){ // helper func to remove passenger from array if found
        boolean removed = false;
        for (int i = 0; i < pArray.length; i++) {
           if (pArray[i].equals(toRemove)){
            pArray[i] = null;
            i = pArray.length;
            removed = true;
           }
        }
        if (!removed){
            System.out.println("Passenger not found");
        }

        pArray = sortPassengers(pArray);
        return pArray;
    }

    public static void addPassenger(Passenger pass, Cabin cab){ // assigns passenger to cabin and vice versa, as a 2 way refference
        try {
            pass.addCabin(cab);
            cab.addPassenger(pass);
        } catch (Exception e) {
            System.out.println("Error adding passenger to cabin in PassengerUtil\n Passenger : " + pass + "\n Cabin : " + cab);// error message
        }
    }

    public static void addPassenger(Passenger pass, Excursion exc){// assigns passenger to excursion and vice versa, as a 2 way refference
        try{
                pass.addExcursion(exc);
                exc.addPassenger(pass);
        }catch(Exception e){
            System.out.println("Error adding passenger to excursion in PassengerUtil\n Passenger : " + pass + "\n Excursion : " + exc); // error message
        }
    }

    public static void addPassenger(Passenger pass, Ship ship){ // assigns passenger to cruise and vice versa, as a 2 way refference
        pass.addShip(ship);
        ship.addPassenger(pass);
    }

    public static void switchCabins(Passenger pass, Cabin cab1, Cabin cab2){ // switches passengers from cabin1 to cabin2
        try {
            cab1.removePassenger(pass); // removes 2 way refference between cab1 and pass
            pass.removeCabin();
    
            addPassenger(pass, cab2); // adds new 2 way refference between cab2 and pass

            System.out.println("Cabin switched successfully");
        } catch (Exception e) {
            System.out.println("Error switching cabins in PassengerUtil\n Passenger : " + pass + "\n Cabin");
        }
    }

    public static Passenger[] removePassenger(Cabin cab, Passenger pass){ // removes passenger from cabin and vice versa, as a 2 way refference
        Passenger[] passengers = cab.getAllPassengers();
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] != null && passengers[i].equals(pass)){ // if passenger is found, remove it
                passengers[i] = null;
                i = passengers.length;
            }
        }

        return passengers;
    }
}


