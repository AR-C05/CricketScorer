package data;

public class BattingRecord {
    private int runs = 0, balls = 0, fours = 0, sixes = 0;

    // setters
    public void increaseRuns(int runs) {
        this.runs += runs;
    }

    public void incrementBalls() {
        balls++;
    }

    public void increaseFours() {
        fours++;
    }

    public void increaseSixes() {
        sixes++;
    }

    // getters
    public int getRuns() {
        return runs;
    }

    public int getBalls() {
        return balls;
    }

    public int getSixes() {
        return sixes;
    }

    public int getFours() {
        return fours;
    }
}
