package data;

public class BowlingRecord {
    private int balls = 0, runs = 0, maidens = 0, wickets = 0;
    
    // setters
    public void increaseBalls() {
        balls++;
    }

    public void increaseRuns(int runs) {
        this.runs += runs;
    }

    public void increaseMaidens() {
        maidens++;
    }

    public void increaseWickets() {
        wickets++;
    }

    // getters
    public int getBalls() {
        return balls;
    }

    public int getRuns() {
        return runs;
    }

    public int getMaidens() {
        return maidens;
    }

    public int getWickets() {
        return wickets;
    }
}
