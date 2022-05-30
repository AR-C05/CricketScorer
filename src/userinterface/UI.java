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
        System.out.print("Enter the name of the home team: ");
        try {
            tempString = input.readLine().strip();
        } catch (IOException e) {
            System.err.println("An error occured with the input");
            e.printStackTrace();
        }
        home = new CricketTeam(tempString);
    }

    public void getName() {
        //
    }
}
