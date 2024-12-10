package CruiseProj.Business;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import CruiseProj.Data.*;
import CruiseProj.Presentation.OutputUtil;

public class ControlFlow {

    public static void menu(Cruise[] cruises) {
        Scanner scn = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        boolean loop = true;
        int userIn;

        OutputUtil.clearTerminal();

        do {
            OutputUtil.clearTerminal(); // clears terminal
            int options = OutputUtil.printMenu(sb); // displays menu and saves the number of options
            userIn = InputUtil.getValidInput(scn, 0, options);

            switch (userIn) {
                case 1: // displays cruise info
                    displayCruiseInfo(sb, scn, cruises);
                    break;
                case 2: // displays excursion info
                    displayExcursionInfo(sb, scn, cruises);
                    break;
                case 3: // displays passenger info
                    displayPassengerInfo(sb, scn, cruises);
                    break;
                case 4: // books passenger onto an excursion
                    bookExcursion(sb, scn, cruises);
                    break;
                case 5: // changes passenger cabin
                    changePassengerCabin(sb, scn, cruises);
                    break;
                case 0: // exits program
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and " + options + ".");
                    break;
            }

        } while (loop);

    }

    // -- main menu functions -- // 
    private static void displayCruiseInfo(StringBuilder sb, Scanner scn, Cruise[] cruises) { // requirement 1 - prints cruise info
        sb.setLength(0);

        boolean loop = true;
        do {
            OutputUtil.clearTerminal();
            Ship selectedShip = selectShip(sb, scn, cruises);

            try {
                OutputUtil.displayShipInfo(sb, selectedShip);
                loop = false;

                System.out.println("Press enter to return to the menu");
                scn.nextLine();
                sb.setLength(0);

            } catch (Exception e) {
                System.out.println("error in displayCruiseInfo");
            }
        } while (loop);
    }

    private static void displayExcursionInfo(StringBuilder sb, Scanner scn, Cruise[] cruises) { // requirement 2 - prints excursion info with more detail
        sb.setLength(0);

        try {
            Ship selectedShip = selectShip(sb, scn, cruises);
            OutputUtil.clearTerminal();

            Excursion selectedExcursion = selectExcursion(sb, scn, selectedShip); // gets the selected excursion
            OutputUtil.clearTerminal();

            OutputUtil.printExcursionDetailed(sb, selectedExcursion); // displays the selected excursion's information
            System.out.println("Press enter to return to the menu");
            scn.nextLine();

        } catch (Exception e) {
            System.out.println("error in displayExcursionInfo");
        }
    }

    private static void displayPassengerInfo(StringBuilder sb, Scanner scn, Cruise[] cruises) {
        sb.setLength(0);

        try {
            Ship selectedShip = selectShip(sb, scn, cruises);
            OutputUtil.clearTerminal();

            Passenger selectedPassenger = selectPassenger(sb, scn, selectedShip); // gets the selected passenger
            OutputUtil.clearTerminal();

            OutputUtil.printPassengerInfo(sb, selectedPassenger); // displays the selected passenger's information

            System.out.println("Press enter to return to the menu");
            scn.nextLine();

        } catch (Exception e) {
            System.out.println("error in displayPassengerInfo");
        }
    }

    private static void bookExcursion(StringBuilder sb, Scanner scn, Cruise[] cruises) {
        sb.setLength(0);

        try {
            Ship SelectedShip = selectShip(sb, scn, cruises);
            OutputUtil.clearTerminal();

            Passenger chosenPassenger = selectPassenger(sb, scn, SelectedShip); // gets the selected passenger
            OutputUtil.clearTerminal();

            ArrayList<Excursion> availableExcursions = getAvailableExcursions(SelectedShip); // gets the available excursions

            Iterator<Excursion> iterator = availableExcursions.iterator(); // using an iterator to change the arrayList while iterating over it
            while (iterator.hasNext()) {
                Excursion excursion = iterator.next();
                for (Excursion excursion2 : chosenPassenger.getExcursions()) {
                    if (excursion.equals(excursion2)) {
                        iterator.remove(); // Safely remove the element during iteration
                        break; // Exit the inner loop as we already found a match
                    }
                }
            }

            if (availableExcursions.size() != 0) {

                OutputUtil.printAvailableExcursions(sb, availableExcursions); // gets user input for excursion
                System.out.println("Select an excursion to book or c to cancel: ");
                String userInput = scn.nextLine();

                if (userInput.toLowerCase().equals("c")) {
                    return;
                }
                else{
                    Excursion chosenExcursion = availableExcursions.get(InputUtil.getValidInput(scn, 0, availableExcursions.size() - 1, userInput)); // user selects an excursion                
    
                    OutputUtil.clearTerminal();
    
                    PassengerUtil.addPassenger(chosenPassenger, chosenExcursion);
                    System.out.println("Excursion booked successfully");
                }
            }

            else{
                System.out.println("No excursions available");
            }

            System.out.println("Press enter to return to the menu");
            scn.nextLine();

        } catch (Exception e) {
            System.out.println("error in bookExcursion");
        }
    }

    private static void changePassengerCabin(StringBuilder sb, Scanner scn, Cruise[] cruises) {
        sb.setLength(0);

        try {
            Ship selectedShip = selectShip(sb, scn, cruises);
            OutputUtil.clearTerminal();

            ArrayList<Cabin> availableCabins = getAvailableCabins(selectedShip); // gets the available cabins
            if (availableCabins.size() != 0) {
                OutputUtil.printAvailableCabins(sb, availableCabins);

                System.out.println("Continue to passenger selection? (y/n)");
                if (scn.nextLine().toLowerCase().equals("y")) {
                    try {
                        OutputUtil.clearTerminal();

                        Passenger selectedPassenger = selectPassenger(sb, scn, selectedShip); // gets the selected passenger
                        OutputUtil.clearTerminal();

                        OutputUtil.printAvailableCabins(sb, availableCabins);
                        System.out.println("Select a cabin to book: ");
                        Cabin selectedCabin = availableCabins.get(InputUtil.getValidInput(scn, 0, availableCabins.size() - 1)); // user selects a cabin
                        OutputUtil.clearTerminal();

                        PassengerUtil.switchCabins(selectedPassenger, selectedPassenger.getCabin(), selectedCabin); // switches the cabins

                    } catch (Exception e) {
                        System.out.println("error in changePassengerCabin");
                    }

                }
            } else {
                System.out.println("No cabins available");
            }

            System.out.println("Press enter to return to menu");
            scn.nextLine();
        } catch (Exception e) {
            System.out.println("error in changePassengerCabin");
        }
    }

    // -- helper functions -- //
    private static Ship selectShip(StringBuilder sb, Scanner scn, Cruise[] cruises) {
        OutputUtil.displayCruiseInfo(sb, scn, cruises);
        System.out.println("Select a ship: ");

        return cruises[InputUtil.getValidInput(scn, 0, cruises.length - 1)].getShip();
    }

    private static Excursion selectExcursion(StringBuilder sb, Scanner scn, Ship ship) {
        OutputUtil.printExcursions(sb, ship.getExcursions());
        System.out.println("Select an excursion: ");

        return ship.getExcursions()[InputUtil.getValidInput(scn, 0, ship.getExcursions().length - 1)];
    }

    private static Passenger selectPassenger(StringBuilder sb, Scanner scn, Ship ship) {
        OutputUtil.listPassengers(sb, ship);
        System.out.println("Select a passenger: ");

        return ship.getPassengers()[InputUtil.getValidInput(scn, 0, ship.getNumPassengers() - 1)];
    }

    private static ArrayList<Excursion> getAvailableExcursions(Ship ship) { // returns excuesions with an available slot
        ArrayList<Excursion> availableExcursions = new ArrayList<Excursion>();

        for (Excursion excursion : ship.getExcursions()) {
            if (excursion.hasSpace()) {
                availableExcursions.add(excursion);
            }
        }
        return availableExcursions;
    }

    private static ArrayList<Cabin> getAvailableCabins(Ship ship) { // returns cabins with an available slot
        ArrayList<Cabin> availableCabins = new ArrayList<Cabin>();
        StandardCabin[] standardCabins = ship.getStandardCabins();

        for (StandardCabin cabin : standardCabins) {
            if (cabin.hasSpace()) {
                availableCabins.add(cabin);
            }
        }

        Suite[] suites = ship.getSuites();
        for (Suite suite : suites) {
            if (suite.hasSpace()) {
                availableCabins.add(suite);
            }
        }
        return availableCabins;
    }
}
