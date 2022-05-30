package data;

public class Cricketer {
    // player identifier
    private Name name;
    // player role
    private boolean captain = false, keeper = false;
    private boolean batted = false, bowled = false;
    // records
    private BattingRecord battingFigures;
    private BowlingRecord bowlingFigures;

    public Cricketer (Name name) {
        this.name = name;
    }

    public void initializeBatting() {
        batted = true;
        battingFigures = new BattingRecord();
    }

    public void initializeBowling() {
        bowled = true;
        bowlingFigures = new BowlingRecord();
    }

    // setters
    public void captain() {
        captain = !captain;
    }

    public void keeper() {
        keeper = !keeper;
    }

    // getters
    public boolean isKeeper() {
        return keeper;
    }

    public boolean isCaptain() {
        return captain;
    }

    public boolean hasBatted() {
        return batted;
    }

    public boolean hasBowled() {
        return bowled;
    }

    public BattingRecord getBatting() {
        return battingFigures;
    }

    public BowlingRecord getBowling() {
        return bowlingFigures;
    }

    public Name getName() {
        return name;
    }
}
