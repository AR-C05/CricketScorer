import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

public class CricketScorer {
    public static enum TossChoice {
        BAT, BOWL
    }

    private static int overs, target;
    private static Team home, away, tossWinner, winner, batting, bowling;
    private static TossChoice choice;
    private static Player striker, nonStriker, bowler;
    private static int legalBallsThisOver = 0, runsThisOver = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        setUp(input);

        if (choice == TossChoice.BAT) {
            batting = tossWinner;
            bowling = tossWinner.equals(home) ? away : home;
        } else {
            batting = tossWinner.equals(home) ? away : home;
            bowling = tossWinner;
        }

        ArrayList<Player> battingXI = batting.getPlayers(), bowlingXI = bowling.getPlayers();

        listBatters(battingXI);

        System.out.print("Enter striker number (1-11): ");
        striker = battingXI.get(Integer.parseInt(uIn(input)) - 1);
        striker.initializeBatting();
        System.out.print("Enter non-striker number (1-11): ");
        nonStriker = battingXI.get(Integer.parseInt(uIn(input)) - 1);
        nonStriker.initializeBatting();

        listBowlers(bowlingXI);

        System.out.print("Enter bowler number (1-11): ");
        bowler = bowlingXI.get(Integer.parseInt(uIn(input)) - 1);
        bowler.initializeBowling();

        while (batting.balls < overs * 6 && batting.wickets < 10) {
            if (legalBallsThisOver == 6)
                endOver(input);
            bowl(input);
        }

        System.out.println("==========END FIRST INNINGS===========");
        displayInnData(batting, bowling);
        target = batting.runs + 1;
        System.out.println("TARGET: " + target + "\t RRR: " + (target / (double) overs));
        System.out.println("======================================");
        System.out.println("======================================");

        Team temp = batting;
        batting = bowling;
        bowling = temp;

        battingXI = batting.getPlayers();
        bowlingXI = bowling.getPlayers();

        listBatters(battingXI);

        System.out.print("Enter striker number (1-11): ");
        striker = battingXI.get(Integer.parseInt(uIn(input)) - 1);
        striker.initializeBatting();
        System.out.print("Enter non-striker number (1-11): ");
        nonStriker = battingXI.get(Integer.parseInt(uIn(input)) - 1);
        nonStriker.initializeBatting();

        listBowlers(bowlingXI);

        System.out.print("Enter bowler number (1-11): ");
        bowler = bowlingXI.get(Integer.parseInt(uIn(input)) - 1);
        bowler.initializeBowling();
        legalBallsThisOver = 0;
        runsThisOver = 0;

        while (batting.balls < overs * 6 && batting.wickets < 10 && batting.runs < target) {
            if (legalBallsThisOver == 6)
                endOver(input);
            bowl(input);
        }

        System.out.println("=========END SECOND INNINGS===========");
        displayInnData(batting, bowling);
        System.out.println("======================================");
        System.out.println("==============END MATCH===============");
        System.out.println("============MATCH SUMMARY=============");
        displayInnData(bowling, batting);
        displayInnData(batting, bowling);
        if (batting.runs != target - 1) {
            winner = batting.runs >= target ? batting : bowling;
            System.out.println("Winner: " + winner.getName());
        } else {
            System.out.println("Match Tied");
        }

        input.close();
    }

    public static void bowl(BufferedReader input) throws IOException {
        System.out.print("Enter ball result (runs 0-7 or wicket or extra): ");
        String result = uIn(input).toLowerCase();
        try {
            int runs = Integer.parseInt(result);
            if (runs < 0 || runs > 7) {
                System.out.println("please enter valid amount");
            } else {
                striker.getBattingFigures().increaseRuns(runs);
                striker.getBattingFigures().incrementBalls();
                if (runs == 4)
                    striker.getBattingFigures().increaseFours();
                else if (runs == 6)
                    striker.getBattingFigures().increaseSixes();
                batting.runs += runs;
                batting.balls++;
                bowler.getBowlingFigures().increaseBalls();
                bowler.getBowlingFigures().increaseRuns(runs);
                if (runs % 2 != 0)
                    changeStrike();
                legalBallsThisOver++;
                runsThisOver += runs;
                return;
            }
        } catch (Exception e) {
            // Do nothing - move on
        }
        if (result.equals("extra")) {
            handleExtras(input);
            return;
        } else if (result.equals("wicket")) {
            handleWicket(input);
            return;
        } else {
            System.out.println("please make a valid selection");
            return;
        }
    }

    public static void endOver(BufferedReader input) throws IOException {
        printEndOver();
        changeStrike();
        if (runsThisOver == 0) {
            bowler.getBowlingFigures().increaseMaidens();
        }
        listBowlers(bowling.getPlayers());
        Player newBowler;
        System.out.print("Select next bowler number (1-11): ");
        newBowler = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);
        while (newBowler.equals(bowler)) {
            System.out.print("Enter NEW bowler (that did not just complete their over): ");
            newBowler = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);
        }
        bowler = newBowler;
        if (!bowler.hasBowled()) {
            bowler.initializeBowling();
        }
        legalBallsThisOver = 0;
        runsThisOver = 0;
    }

    public static void printEndOver() {
        System.out.println("==========================");
        System.out.printf("Overs: %s\tRuns Scored: %d\n", batting.getOvers(), runsThisOver);
        System.out.println("Score: " + batting.getName() + ": " + batting.runs + "-" + batting.wickets);
        System.out.printf("%s  %d(%d)\t%s  %d(%d)\n",
                striker.getName(),
                striker.getBattingFigures().getRuns(),
                striker.getBattingFigures().getBalls(),
                nonStriker.getName(),
                nonStriker.getBattingFigures().getRuns(),
                nonStriker.getBattingFigures().getBalls());
        System.out.printf("%s  %s\n", bowler.getName(), bowler.getBowlingFigures().toString());
        System.out.println("==========================");
    }

    public static void changeStrike() {
        Player temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    public static void displayInnData(Team batting, Team bowling) {
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println(batting.getName() + ": " + batting.runs + "-" + batting.wickets + " (" +
                batting.getOvers() + ")");
        System.out.println();
        System.out.println("Batter\tRuns\tBalls\t4s\t6s\tSR");
        for (Player batter : batting.getPlayers()) {
            if (batter.hasBatted()) {
                System.out.println(batter.getName() + "\t " + batter.getBattingFigures().toString());
                if (batter.isGotOut()) {
                    System.out.println("---" + batter.getDismissal() + "---");
                } else {
                    System.out.println("---not out---");
                }
            }
        }
        for (Player batter : batting.getPlayers()) {
            if (!batter.hasBatted()) {
                System.out.println(batter.getName() + "\t Did Not Bat");
            }
        }
        System.out.println();
        System.out.println("Bowler\tOvers\tMaidens\tRuns\tWickets\tEconomy");
        for (Player bowler : bowling.getPlayers()) {
            if (bowler.hasBowled()) {
                System.out.println(bowler.getName() + "\t " + bowler.getBowlingFigures().toString());
            }
        }
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("==================================================");
    }

    public static void handleWicket(BufferedReader input) throws IOException {
        Set<String> wicketTypes = Set.of(
                "bowled", "caught", "stumped", "lbw",
                "run out", "caught and bowled", "hit wicket");
        String userInput = "";
        System.out.println(wicketTypes.toString());
        while (!wicketTypes.contains(userInput)) {
            System.out.print("Enter wicket type: ");
            userInput = uIn(input);
        }
        switch (userInput) {
            case "bowled":
                bowled(input);
                break;
            case "caught":
                caught(input);
                break;
            case "stumped":
                stumped(input);
                break;
            case "lbw":
                lbw(input);
                break;
            case "run out":
                runOut(input);
                break;
            case "caught and bowled":
                cAndB(input);
                break;
            case "hit wicket":
                hitWicket(input);
                break;
            default:
                break;
        }
    }

    public static Player newBatter(BufferedReader input) throws IOException {
        Player newBat;
        listBatters(batting.getPlayers());
        System.out.print("Select next batter (1-11): ");
        newBat = batting.getPlayers().get(Integer.parseInt(uIn(input)) - 1);
        while (newBat.hasBatted()) {
            System.out.print("Select a NEW batter: ");
            newBat = batting.getPlayers().get(Integer.parseInt(uIn(input)) - 1);
        }
        return newBat;
    }

    public static void bowled(BufferedReader input) throws IOException {
        String dismissal = "b " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);
        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;

        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
        }
    }

    public static void lbw(BufferedReader input) throws IOException {
        String dismissal = "lbw " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);
        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;

        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
        }
    }

    public static void caught(BufferedReader input) throws IOException {
        Player catcher;
        System.out.println(bowling.listPlayers());
        System.out.print("Select fielder: ");
        catcher = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);

        String dismissal = "c " + catcher.getName() + " b " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);

        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;
        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
            System.out.println("Crosses (y/n)? ");
            if (uIn(input).equalsIgnoreCase("y")) {
                changeStrike();
            }
        }
    }

    public static void stumped(BufferedReader input) throws IOException {
        Player keeper;
        System.out.println(bowling.listPlayers());
        System.out.print("Select keeper: ");
        keeper = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);

        String dismissal = "st " + keeper.getName() + " b " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);

        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;
        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
            System.out.println("Crosses (y/n)? ");
            if (uIn(input).equalsIgnoreCase("y")) {
                changeStrike();
            }
        }
    }

    public static void runOut(BufferedReader input) throws IOException {
        Player fielder1, fielder2, notOut;
        String dismissal;

        System.out.print("Direct hit (y/n)? ");
        String userInput = uIn(input).toLowerCase();

        System.out.println(bowling.listPlayers());
        if (userInput.equals("y")) {
            System.out.print("Select fielder: ");
            fielder1 = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);

            dismissal = String.format("run out (%s)", fielder1.getName());
        } else {
            System.out.print("Select the first fielder: ");
            fielder1 = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);
            System.out.print("Select second fielder: ");
            fielder2 = bowling.getPlayers().get(Integer.parseInt(uIn(input)) - 1);

            dismissal = String.format("run out (%s/%s)", fielder1.getName(), fielder2.getName());
        }

        System.out.print("Runs completed (0-3): ");
        int runs = Integer.parseInt(uIn(input));

        striker.getBattingFigures().incrementBalls();
        striker.getBattingFigures().increaseRuns(runs);
        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseRuns(runs);
        batting.runs += runs;
        runsThisOver += runs;
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;

        System.out.printf("1. %s\n2. %s\n", striker.getName(), nonStriker.getName());
        System.out.print("Select out batter (1 or 2):");
        userInput = uIn(input);
        if (userInput.equals("1")) {
            striker.setDismissal(dismissal);
            striker.setGotOut(true);
            notOut = nonStriker;
        } else {
            nonStriker.setDismissal(dismissal);
            nonStriker.setGotOut(true);
            notOut = striker;
        }

        if (batting.wickets < 10) {
            Player newBat = newBatter(input);
            newBat.initializeBatting();
            System.out.printf("1. %s\n2. %s\n", newBat.getName(), notOut.getName());
            System.out.print("Select batter on strike (1 or 2):");
            userInput = uIn(input);
            if (userInput.equals("1")) {
                striker = newBat;
                nonStriker = notOut;
            } else {
                striker = notOut;
                nonStriker = newBat;
            }
        }
    }

    public static void cAndB(BufferedReader input) throws IOException {
        String dismissal = "c & b " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);
        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;

        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
        }
    }

    public static void hitWicket(BufferedReader input) throws IOException {
        String dismissal = "hit wicket b " + bowler.getName();
        striker.getBattingFigures().incrementBalls();
        striker.setDismissal(dismissal);
        striker.setGotOut(true);
        bowler.getBowlingFigures().increaseBalls();
        bowler.getBowlingFigures().increaseWickets();
        batting.balls++;
        batting.wickets++;
        legalBallsThisOver++;

        if (batting.wickets < 10) {
            striker = newBatter(input);
            striker.initializeBatting();
        }
    }

    public static void handleExtras(BufferedReader input) throws IOException {
        System.out.print("Enter extra type (wd, nb, lb, b): ");
        String extraType = uIn(input).toLowerCase();
        if (extraType.equals("wd")) {
            wide(input);
        } else if (extraType.equals("nb")) {
            noBall(input);
        } else if (extraType.equals("lb")) {
            legBye(input);
        } else if (extraType.equals("b")) {
            bye(input);
        } else {
            System.out.println("please select a valid choice");
            handleExtras(input);
            return;
        }
    }

    public static void noBall(BufferedReader input) throws IOException {
        System.out.print("additional runs scored (y/n): ");
        String result = uIn(input).toLowerCase();
        if (result.equals("y")) {
            result = "";
            while (!(result.equals("y") || result.equals("n"))) {
                System.out.print("Off the bat (y/n): ");
                result = uIn(input).toLowerCase();
            }
            if (result.equals("y")) {
                int runs = 0;
                while (runs < 1 || runs > 7) {
                    System.out.print("Enter _total_ number of runs (1-7): ");
                    result = uIn(input);
                    try {
                        runs = Integer.parseInt(result);
                    } catch (Exception e) {
                        System.out.println("Please enter a number.");
                    }
                }
                striker.getBattingFigures().incrementBalls();
                striker.getBattingFigures().increaseRuns(runs - 1);
                if (runs - 1 == 4)
                    striker.getBattingFigures().increaseFours();
                else if (runs - 1 == 6)
                    striker.getBattingFigures().increaseSixes();
                bowler.getBowlingFigures().increaseRuns(runs);
                batting.runs += runs;
                runsThisOver += runs;
                if (runs % 2 == 0)
                    changeStrike();
            } else {
                int runs = 0;
                while (runs <= 0 || runs > 5) {
                    System.out.print("Enter _total_ number of runs (1-5): ");
                    result = uIn(input);
                    try {
                        runs = Integer.parseInt(result);
                    } catch (Exception e) {
                        System.out.println("Please enter a number.");
                    }
                }
                striker.getBattingFigures().incrementBalls();
                bowler.getBowlingFigures().increaseRuns(runs);
                batting.runs += runs;
                runsThisOver += runs;
                if (runs % 2 == 0)
                    changeStrike();
            }
        } else if (result.equals("n")) {
            striker.getBattingFigures().incrementBalls();
            bowler.getBowlingFigures().increaseRuns(1);
            batting.runs++;
            runsThisOver++;
        } else {
            System.out.println("please select a valid choice");
            noBall(input);
        }
    }

    public static void bye(BufferedReader input) throws IOException {
        System.out.print("Enter number of runs (1-6): ");
        String result = uIn(input);
        try {
            int runs = Integer.parseInt(result);
            if (runs < 1 || runs > 6) {
                System.out.println("please enter valid amount");
                bye(input);
                return;
            }
            striker.getBattingFigures().incrementBalls();
            bowler.getBowlingFigures().increaseBalls();
            batting.runs += runs;
            batting.balls++;
            legalBallsThisOver++;
            if (runs % 2 != 0)
                changeStrike();
        } catch (Exception e) {
            System.out.println("please enter valid amount");
            bye(input);
            return;
        }
    }

    public static void legBye(BufferedReader input) throws IOException {
        System.out.print("Enter number of runs (1-6): ");
        String result = uIn(input);
        try {
            int runs = Integer.parseInt(result);
            if (runs < 1 || runs > 6) {
                System.out.println("please enter valid amount");
                legBye(input);
                return;
            }
            striker.getBattingFigures().incrementBalls();
            bowler.getBowlingFigures().increaseBalls();
            batting.runs += runs;
            batting.balls++;
            legalBallsThisOver++;
            if (runs % 2 != 0)
                changeStrike();
        } catch (Exception e) {
            System.out.println("please enter valid amount");
            legBye(input);
            return;
        }
    }

    public static void wide(BufferedReader input) throws IOException {
        System.out.print("Enter _total_ number of runs (1-5): ");
        String result = uIn(input);
        try {
            int runs = Integer.parseInt(result);
            if (runs < 1 || runs > 7) {
                System.out.println("please enter valid amount");
                wide(input);
                return;
            }
            bowler.getBowlingFigures().increaseRuns(runs);
            batting.runs += runs;
            runsThisOver += runs;
            if (runs % 2 == 0)
                changeStrike();
        } catch (Exception e) {
            System.out.println("please enter valid amount");
            wide(input);
            return;
        }
    }

    public static void listBatters(ArrayList<Player> battingXI) {
        Player player;
        for (int i = 0; i < battingXI.size(); i++) {
            player = battingXI.get(i);
            System.out.print(Integer.toString(i + 1) + ". " + player.getName());
            if (player.hasBatted()) {
                System.out.println("\t " + player.getBattingFigures().toString());
                if (player.isGotOut()) {
                    System.out.println("  ---" + player.getDismissal() + "---");
                } else {
                    System.out.println("  ---not out---");
                }
            } else {
                System.out.println("\t Yet To Bat");
            }
        }
    }

    public static void listBowlers(ArrayList<Player> bowlingXI) {
        Player player;
        for (int i = 0; i < bowlingXI.size(); i++) {
            player = bowlingXI.get(i);
            System.out.print(Integer.toString(i + 1) + ". " + player.getName());
            if (player.hasBowled()) {
                System.out.println("\t " + player.getBowlingFigures().toString());
            } else {
                System.out.println("\t Yet To Bowl");
            }
        }
    }

    private static void setUp(BufferedReader input) throws IOException {
        String userInput = "";
        System.out.print("Num overs: ");
        userInput = uIn(input);

        overs = Integer.parseInt(userInput);

        System.out.print("Home team: ");
        String homeName = uIn(input);
        home = new Team(homeName);

        System.out.print("Away team: ");
        String awayName = uIn(input);
        away = new Team(awayName);

        System.out.print("Toss winner ('home' or 'away'):");
        userInput = uIn(input);
        if (userInput.equalsIgnoreCase("away")) {
            tossWinner = away;
        } else {
            tossWinner = home;
        }
        System.out.print("Toss choice ('bat' or 'bowl')");
        choice = uIn(input).equalsIgnoreCase("bat") ? TossChoice.BAT : TossChoice.BOWL;

        System.out.println("=============================================");
        getPlayingXI(home, input);
        System.out.println("=============================================");
        getPlayingXI(away, input);

    }

    private static void getPlayingXI(Team team, BufferedReader input) throws IOException {
        //
        System.out.println("Enter playing squad of " + team.getName() + ":");
        int i = 1;
        while (i < 12) {
            System.out.print("Enter unique player " + i + ": ");
            Player newPlayer = new Player(uIn(input));
            if (team.addPlayer(newPlayer)) {
                i++;
            } else {
                System.out.println("Please ensure unique player");
            }
        }
    }

    private static String uIn(BufferedReader input) throws IOException {
        return input.readLine().strip();
    }
}
