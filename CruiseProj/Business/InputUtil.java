package CruiseProj.Business;

import java.util.Scanner;

public class InputUtil { // util class to take input from user and ensure it fits specific requirements before allowing it to continue

    public static int getValidInput(Scanner scn, int min, int max) { // method to get valid input from user
        while(true){
            try {
                int userIn = Integer.parseInt(scn.nextLine()); // using parseInt instaed of nextInt to avoid skipping nextLine
                if (userIn >= min && userIn <= max) {
                    return userIn;
                } else {
                    System.out.println("Invalid input. Please enter a number between 0 and " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number");
            }
        }
    }
}
