package CruiseProj.Business;

import java.util.Scanner;

public class InputUtil { // util class to take input from user and ensure it fits specific requirements before allowing it to continue

    public static int getValidInput(Scanner scn, int min, int max) {
        return getValidInput(scn, min, max, null);
    }
    
    public static int getValidInput(Scanner scn, int min, int max, String starterString) {
        if (starterString != null && !starterString.trim().isEmpty()) {
            try {
                int startInt = parseAndValidateInput(starterString.trim(), min, max);
                System.out.println(starterString);
                return startInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return promptForValidInput(scn, min, max);
    }
    
    private static int parseAndValidateInput(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) {
                return value;
            } else {
                throw new IllegalArgumentException("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
        }
    }
    
    private static int promptForValidInput(Scanner scn, int min, int max) {
        while (true) {
            try {
                String userInput = scn.nextLine();
                return parseAndValidateInput(userInput, min, max);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    


}
