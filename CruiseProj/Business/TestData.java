package CruiseProj.Business;

import java.util.Random;

import CruiseProj.Data.Cabin;
import CruiseProj.Data.Cruise;
import CruiseProj.Data.DayOfWeek;
import CruiseProj.Data.Excursion;
import CruiseProj.Data.Passenger;
import CruiseProj.Data.Port;
import CruiseProj.Data.Ship;
import CruiseProj.Data.StandardCabin;
import CruiseProj.Data.Suite;

public class TestData { // class to create test data
        // used to create test data for the project
        private static Random rand = new Random();
        private static Cruise[] cruises = new Cruise[3];
        private static final String[] passengerNames = {"John Smith", "Jane Doe", "Emily Johnson", "Michael Brown", "Sarah Davis", "David Wilson", "Emma Thompson", "James Garcia", "Olivia Martinez", "Liam Anderson", "Sophia Taylor", "Benjamin Moore", "Charlotte Lee", "Lucas White", "Amelia Harris", "Mason Clark", "Isabella Lewis", "Ethan Walker", "Mia Young", "Alexander Allen", "Noah Scott", "Ava King", "William Perez", "Chloe Hill", "Elijah Adams", "Harper Nelson", "Logan Carter", "Ella Roberts", "Jackson Evans", "Abigail Cooper", "Sebastian Stewart", "Grace Edwards", "Daniel Flores", "Victoria Torres", "Matthew Brooks", "Lily Reed", "Henry Bailey", "Zoe Barnes", "Samuel Powell", "Hannah Rivera", "Jack Cook", "Scarlett Bell", "Aiden Murphy", "Layla Patterson", "Joseph Ward", "Aria Hughes", "Owen Ramirez", "Penelope Bennett", "Gabriel Torres", "Ellie Russell"};
        private static final String[] shipNames = {"The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic", "The Sea Breeze", "The Ocean Voyager", "The Sea Explorer", "The Oceanic", "The Sea Explorer", "The Oceanic"};
        private static final Port[] ports = {new Port("Port of Miami"), new Port("Port of Los Angeles"), new Port("Port of New York"), new Port("Port of Long Beach"), new Port("Port of Houston"), new Port("Port of New Orleans"), new Port("Port of Seattle"), new Port("Port of Charleston"), new Port("Port of San Francisco"), new Port("Port of Savannah")};

        public static Cruise[] buildTestData(){
            for (int i = 0; i < cruises.length; i++) {
                cruises[i] = createCruise(shipNames, passengerNames, ports);
            }
            return cruises;
        }

        public static Cruise createCruise(String[] shipNames, String[] passengerNames, Port[] ports) {
            Ship ship = createShip(shipNames);
            Cruise cruise = new Cruise(ship);
    
            fillStandardCabins(ship, passengerNames);
            fillSuites(ship, passengerNames);
            fillExcursions(cruise, ship, ports);
    
            return cruise;
        }

         // ship manipulation
        private static Ship createShip(String[] shipNames) { // creates a ship with a random name
            String shipName = shipNames[rand.nextInt(shipNames.length)];
            return new Ship(shipName, 20, 10);  // 20 standard cabins, 10 suites just for dummy data
        }

        // cabin manipulation
        private static void fillStandardCabins(Ship ship, String[] passengerNames) { // instantiates and fills standard cabins on ship with passengers
        System.out.println("Filling standard cabins...");
        int cabinCounter = 1;

        for (int i = 0; i < ship.getNumStandardCabins(); i++) {
            StandardCabin cabin = new StandardCabin(rand.nextBoolean(), cabinCounter++); // builds cabin then inrements counter
            ship.addStandardCabin(cabin);
            assignRandomPassengersToCabin(cabin, ship, passengerNames, 7); // Max 6 passengers per cabin
        }
    }

     private static void fillSuites(Ship ship, String[] passengerNames) { // instantiates and fills suites on ship with passengers
        System.out.println("Filling suites...");
        int cabinCounter = ship.getNumStandardCabins() + 1;

        for (int i = 0; i < ship.getNumSuites(); i++) {
            Suite suite = new Suite(rand.nextBoolean(), cabinCounter++);
            ship.addSuite(suite);
            assignRandomPassengersToCabin(suite, ship, passengerNames, 5); // Max 4 passengers per suite
        }
    }

    private static void assignRandomPassengersToCabin(Cabin cabin, Ship ship, String[] passengerNames, int maxPassengers) { // creates and assigns random passengers to cabin and ship
        int numPassengers = rand.nextInt(maxPassengers);
        for (int j = 0; j < numPassengers; j++) {
            Passenger passenger = new Passenger(passengerNames[rand.nextInt(passengerNames.length)]);
            PassengerUtil.addPassenger(passenger, cabin);
            PassengerUtil.addPassenger(passenger, ship);
        }
    }

    //excursion manipulation
    private static void fillExcursions(Cruise cruise, Ship ship, Port[] ports) { // instantiates and fills excursions on cruise with passengers
        System.out.println("Filling excursions...");
        int passengerCounter = 0;

        for (int i = 0; i < cruise.getNumExcursions(); i++) {
            Excursion excursion = createExcursion(ports);
            cruise.addExcursion(excursion);
            ship.addExcursion(excursion);

            assignPassengersToExcursion(excursion, ship, passengerCounter);
        }
    }

    private static Excursion createExcursion(Port[] ports) { // creates an excursion with a random port and day
        Port port = ports[rand.nextInt(ports.length)];
        DayOfWeek day = DayOfWeek.values()[rand.nextInt(DayOfWeek.values().length)];
        return new Excursion(port, day, 10); // Example max excursion size
    }

    private static void assignPassengersToExcursion(Excursion excursion, Ship ship, int passengerCounter) { // assigns passengers to excursion
        int excursionPassengerCount = 0;
        while (excursionPassengerCount < 9 && passengerCounter < ship.getPassengers().length) { // maximum of 9 people per excursion for testing, so that passenger can be added to any excursion
            Passenger passenger = ship.getPassengers()[passengerCounter++]; // iterate through ships passengers then increment counter
            PassengerUtil.addPassenger(passenger, excursion);
            excursionPassengerCount++;
        }
    }


}
