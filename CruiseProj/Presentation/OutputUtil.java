package CruiseProj.Presentation;

import java.lang.classfile.instruction.ThrowInstruction;
import java.util.ArrayList;
import java.util.Scanner;

import CruiseProj.Data.Cabin;
import CruiseProj.Data.Cruise;
import CruiseProj.Data.Excursion;
import CruiseProj.Data.Passenger;
import CruiseProj.Data.Ship;

public class OutputUtil {
    // constants to avoid magic number

    private static final int LINE_WIDTH = 49;
    private static final String LINE_SEPARATOR = "--------------------------------------------------";

    // -- general output functions -- // 
    public static int printMenu(StringBuilder sb) { // main method for printing the menu
        sb.setLength(0);

        printHeader(sb, " == Menu ==");
        String[] menuItems = { // array of menu items rather than hard coding to try get into better habits
            "1 - Display Cruise Information",
            "2 - Display Excursion Information",
            "3 - Display Passenger Information",
            "4 - Book Excursion",
            "5 - Change Passenger Cabin",
            "0 - Exit"
        };

        for (String item : menuItems) { // displays the menu items in a formatted table
            sb.append("| ").append(item);
            addPadding(sb, LINE_WIDTH - 2 - item.length());
            sb.append("|\n");
        }
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
        sb.setLength(0); // clears stringbuilder

        return 5; // returns the number of menu items
    }

    public static void printHeader(StringBuilder sb, String headerString) { // prints a formatted header
        if (!headerString.isEmpty()) {
            sb.append(headerString).append("\n");
        }
        sb.append(LINE_SEPARATOR).append("\n");
    }

    private static void addPadding(StringBuilder sb, int padding) { // adds padding to stringbuilder
        sb.append(" ".repeat(padding)); // never heard of .repeat until now but this is kinda fun
    }

    public static void clearTerminal() { // clears the terminal contents
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // used to be using an ANSI escape string but out of nowhere it started having issues with the output stream? really weird it was duplicating the contents of stringBuilder even though it did not use it at all.... no clue so just a bunch of newlines
    }

    private static void appendKeyValue(StringBuilder sb, String key, String value) { // trying to append key-value pair with dynamic padding, to help with table formatting. saw a similar approach in friends code and wante to try use it
        sb.append("| ").append(key).append(": ").append(value);
        addPadding(sb, LINE_WIDTH - 4 - key.length() - value.length());
        sb.append("|\n");
    }

    // -- cruise output functions -- // 
    public static void displayCruiseInfo(StringBuilder sb, Scanner scn, Cruise[] cruises) { // prints the ships from an array of cruises
        clearTerminal(); // clearing stringBuilder and terminal
        sb.setLength(0);

        int counter = 0;
        printHeader(sb, " == Select a ship ==");
        for (Cruise cruise : cruises) {
            Ship ship = cruise.getShip();
            appendKeyValue(sb, String.valueOf(counter++), ship.getName());
        }
        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
    }

    public static void displayShipInfo(StringBuilder sb, Ship ship) { // prints the ship info
        clearTerminal(); // clearing stringBuilder and terminal
        sb.setLength(0);

        printHeader(sb, " == Ship Details == " );
        appendKeyValue(sb, "Name", ship.getName());
        appendKeyValue(sb, "Passengers on board", String.valueOf(ship.getNumPassengers()));

        sb.append(LINE_SEPARATOR).append("\n");

        printHeader(sb, " == Excursions Scheduled == ");
        printExcursionsHelper(sb, ship.getExcursions());

        System.out.println(sb.toString());
    }

    //  -- excursion output functions -- //
    public static void printExcursions(StringBuilder sb, Excursion[] excursions) { // called by displayCruiseInfo
        clearTerminal(); // clearing stringBuilder and terminal
        sb.setLength(0);

        for (int i = 0; i < excursions.length; i++) {
            Excursion excursion = excursions[i];
            printHeader(sb, " == Excursion - " + i + " ==");
            appendKeyValue(sb, "Destination", excursion.getDestination().getName());
            appendKeyValue(sb, "Day", excursion.getDay().toString());
            appendKeyValue(sb, "Max Passengers", String.valueOf(excursion.getMaxPassengers()));
            appendKeyValue(sb, "Current Passengers", excursion.getCurrentPassengers() + "/10");

            if (excursion.getCurrentPassengers() < excursion.getMaxPassengers()) {
                appendKeyValue(sb, "Available Spaces",
                        String.valueOf(excursion.getMaxPassengers() - excursion.getCurrentPassengers()));
            } else {
                sb.append("| ###### Excursion is full ######");
                addPadding(sb, LINE_WIDTH - 33);
            }
            sb.append(LINE_SEPARATOR).append("\n");
        }
        System.out.println(sb.toString());
        sb.setLength(0); // clears stringbuilder again
    }

    public static void printExcursionDetailed(StringBuilder sb, Excursion excursion) { //internal func that prints the more detailed excursion information
        sb.setLength(0); // reset stringbuilder
        printHeader(sb, " == Excursion Details ==");
        appendKeyValue(sb, "Destination", excursion.getDestination().getName());
        appendKeyValue(sb, "Day", excursion.getDay().toString());
        appendKeyValue(sb, "Passengers", String.valueOf(excursion.getCurrentPassengers()));
        if (excursion.getCurrentPassengers() > 0) {
            for (int i = 0; i < excursion.getCurrentPassengers(); i++) {
                appendKeyValue(sb, " " + i, excursion.getPassengers()[i].getName()); // prints all passengers attatched to their excursion
            }
        }
        else{
            sb.append("| ###### No passengers on this excursion ###### ");
            addPadding(sb, LINE_WIDTH - 43);
        }
        sb.append(LINE_SEPARATOR).append("\n");
        System.out.println(sb.toString());
    }

    public static void printExcursionsHelper(StringBuilder sb, Excursion[] excursions){
        for (int i = 0; i < excursions.length; i++) {
            Excursion excursion = excursions[i];

            appendKeyValue(sb, excursion.getDestination().getName(), "");
            appendKeyValue(sb, "Day", excursion.getDay().toString());
            appendKeyValue(sb, "Available spaces", String.valueOf(excursion.getMaxPassengers() - excursion.getCurrentPassengers()));
            if (i != excursions.length - 1) {
                sb.append("|" + " ".repeat(LINE_WIDTH - 1) + "|\n");
            }
        }

        sb.append(LINE_SEPARATOR).append("\n");
    }

    public static int listPassengers(StringBuilder sb, Ship ship) { // prints the passengers on a cruise
        sb.setLength(0);
        Passenger[] passList = ship.getPassengers();
        int counter = 0;

        printHeader(sb, " == Select a passenger ==");
        for (Passenger pass : passList) {
            if (pass != null) {
                appendKeyValue(sb, String.valueOf(counter++), pass.getName());
            }
        }

        sb.append(LINE_SEPARATOR);
        System.out.println(sb.toString());
        return counter;
    }

    public static void printPassengerInfo(StringBuilder sb, Passenger passenger) { // prints the passenger info
        sb.setLength(0);

        Passenger[] cabinMates = passenger.getCabin().getAllPassengers();

        printHeader(sb, "Passenger Details - " + passenger.getName()); // general info
        appendKeyValue(sb, "Name", passenger.getName());
        appendKeyValue(sb, "Cabin", String.valueOf(passenger.getCabin().getNum()));
        appendKeyValue(sb, "Cabin type", passenger.getCabin().getType());

        appendKeyValue(sb, "Cabin Mates", String.valueOf(passenger.getCabin().getNumPassengers() - 1)); // cabin mate info
        for (int i = 0; i < cabinMates.length; i++) { // lists the cabin mates
            if (cabinMates[i] != null && !cabinMates[i].getName().equals(passenger.getName())) { // if cabin mate is not null and not the same apssenger
                appendKeyValue(sb, String.valueOf(i), cabinMates[i].getName());
            }
        }

        appendKeyValue(sb, "Excursions", String.valueOf(passenger.getExcursions().size())); // excursion info
        for (int i = 0; i < passenger.getExcursions().size(); i++) { // lists the excursions
            appendKeyValue(sb, String.valueOf(i), passenger.getExcursions().get(i).getDestination().getName());
        }
        // when testing this method, passengers towards the end of the list do not have excursions becoause of how created the test data, so choose early pass in list

        sb.append(LINE_SEPARATOR).append("\n");
        System.out.println(sb.toString());
    }

    public static void printAvailableExcursions(StringBuilder sb, ArrayList<Excursion> excursions) {
        sb.setLength(0);

        printHeader(sb, " == Available Excursions ==");

        if (excursions.size() == 0) {
            sb.append("| ###### No excursions available ######");
            addPadding(sb, LINE_WIDTH - 33);
        }
        else{   
            for (int i = 0; i < excursions.size(); i++) {
                Excursion excursion = excursions.get(i);
                appendKeyValue(sb, String.valueOf(i), excursion.getDestination().getName());
            }
        }

        sb.append(LINE_SEPARATOR).append("\n");
        System.out.println(sb.toString());
    }

    public static void printAvailableCabins(StringBuilder sb, ArrayList<Cabin> cabins) {
        sb.setLength(0);

        printHeader(sb, " == Available Cabins ==");
        for (int i = 0; i < cabins.size(); i++) {
            Cabin cabin = cabins.get(i);
            appendKeyValue(sb, String.valueOf(i), cabin.getType());
        }

        sb.append(LINE_SEPARATOR).append("\n");
        System.out.println(sb.toString());
    }

    // Similar methods for Ships, Passengers, and Cabins...
}
