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
            } catch (IOException e) {
                System.out.println("Please enter a valid input");
            }
        }
        return finalStr;
    }

    public static int getUserIntInput() {
        //
        return 0;
    }

    private boolean isInt() {
        //
        return true;
    }

}
