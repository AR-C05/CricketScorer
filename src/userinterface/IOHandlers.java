package userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHandlers {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // gets simple String input (not empty) from user and returns it
    public static String getUserStringInput() {
        String finalStr = "";
        while (finalStr.isBlank()) {
            try {
                finalStr = input.readLine().strip();
                if (finalStr.isBlank())
                    System.out.println("No value entered");
            } catch (IOException e) {
                System.out.println("Please enter a valid input");
            }
        }
        return finalStr;
    }

    public static int getUserIntInput() {
        String inputStr = getUserStringInput();
        while (!isInt(inputStr)) {
            System.out.print("=== Please enter an integer value: ");
            inputStr = getUserStringInput();
        }
        return Integer.parseInt(inputStr);
    }

    /**
     * determines if provided string is an integer
     * 
     * @return true if the entry is an integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * determines if provided string is a double
     * 
     * @return true if the entry is a double
     */
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
