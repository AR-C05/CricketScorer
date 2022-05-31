package scorer;

import data.CricketTeam;

/**
 * Scorer class. Parent class to score a cricket match.
 * <p>
 * Date Created: 2022-05-29
 * <p>
 * Date Updated: 2022-05-30
 * 
 * @author AR.C
 */
public class Scorer {

    public static enum TossChoice {BAT, BOWL}

    private CricketTeam homeTeam, awayTeam;
    private CricketTeam tossWinner;
    private TossChoice choice;

    // setters
    public void setTossWinner(CricketTeam winner) {
        tossWinner = winner;
    }

    public void setHomeTeam(CricketTeam team) {
        homeTeam = team;
    }

    public void setAwayTeam(CricketTeam team) {
        awayTeam = team;
    }

    public void setTossChoice(TossChoice choice) {
        this.choice = choice;
    }

    // getters
    public CricketTeam getHomeTeam() {
        return homeTeam;
    }

    public CricketTeam getAwayTeam() {
        return awayTeam;
    }

    public CricketTeam getTossWinTeam() {
        return tossWinner;
    }

    public TossChoice getTossChoice() {
        return choice;
    }

}
