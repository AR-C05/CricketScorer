public class Player {
    private String name;
    private BowlingRecord bowlingFigures;
    private BattingRecord battingFigures;
    private boolean batted = false, bowled = false, gotOut = false;
    private String dismissal;
    
    public Player(String name) {
        this.name = name;
    }

    public boolean isGotOut() {
        return gotOut;
    }

    public void setGotOut(boolean gotOut) {
        this.gotOut = gotOut;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public String getName() {
        return name;
    }

    public BowlingRecord getBowlingFigures() {
        return bowlingFigures;
    }

    public BattingRecord getBattingFigures() {
        return battingFigures;
    }

    public void initializeBatting() {
        batted = true;
        battingFigures = new BattingRecord();
    }

    public void initializeBowling() {
        bowled = true;
        bowlingFigures = new BowlingRecord();
    }

    public boolean hasBatted() {
        return batted;
    }

    public boolean hasBowled() {
        return bowled;
    }

    public boolean equals(Player other) {
        return this.name.equalsIgnoreCase(other.name);
    }

}
