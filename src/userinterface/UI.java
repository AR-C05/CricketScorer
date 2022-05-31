package userinterface;

import data.CricketTeam;
import data.Name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    
    public static void main(String[] args) throws IOException {
        // TODO finsish
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        CricketTeam home, away;
        String tempString = "";
        try {
            tempString = input.readLine().strip();
        } catch (IOException e) {
            System.err.println("An error occured with the input");
            e.printStackTrace();
        }
        home = new CricketTeam(tempString);
    }

    public static String getTeamName(String homeOrAway, BufferedReader input) {
        String userInput = "".strip();
        while (userInput.strip().isBlank() || userInput) {
            try {
                System.out.print("Enter the name of the " + homeOrAway + " team: " );
                userInput = input.readLine().strip();
            } catch (IOException e) {
            System.out.println("Please enter a valid team name (letters and/or numbers)");
            e.printStackTrace();
        }}

    }
}
