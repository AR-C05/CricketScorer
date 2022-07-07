package userinterface;

import data.CricketTeam;
import data.Formatters;
import data.Name;
import userinterface.IOHandlers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        CricketTeam home, away;
        String tempString = "";
        home = new CricketTeam(getTeamName("home", input));
        tempString = getTeamName("away", input);
        while (tempString.equalsIgnoreCase(home.getName())) {
            tempString = getTeamName("away", input);
        }
    }

    public static void initiateSquad(CricketTeam team) {
        String userInput = "";
        while (!userInput.equalsIgnoreCase("q")) {
            System.out.println("Enter player name to add to squad (q to finish): ");
        }
    }

    public static String getTeamName(String homeOrAway, BufferedReader input) {
        String userInput = "";
        while (userInput.isBlank()) {
            try {
                System.out.print("Enter the name of the " + homeOrAway + " team: ");
                userInput = input.readLine().strip();
            } catch (IOException e) {
                System.out.println("Please enter a valid team name (letters and/or numbers)");
                e.printStackTrace();
            }
        }
        return Formatters.toTitleCase(userInput);
    }

    public static String getPlayerName(CricketTeam team, BufferedReader input) {
        // TODO
        String userInput = "";
        while (userInput.isBlank()) {
            System.out.println("Enter the player's name");
            userInput = IOHandlers.getUserStringInput();
        }
        return Formatters.toTitleCase(userInput);
    }
}
