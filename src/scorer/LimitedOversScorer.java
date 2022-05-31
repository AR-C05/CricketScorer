package scorer;

/**
 * LimitedOversScorer class. For scoring a Limited Overs cricket match. Extends {@link Scorer}
 * <p>
 * Date Created: 2022-05-29
 * <p>
 * Date Updated: 2022-05-30
 * 
 * @author AR.C
 */
public class LimitedOversScorer extends Scorer {
    private int powerPlayOvers, powerPlay2Overs, oversPerInnings;

    public LimitedOversScorer (int overs) {
        super();
        oversPerInnings = overs;
    }

    // setters
    public void setPowerPlayOvers(int overs) {
        powerPlayOvers = overs;
    }

    public void setPowerPlay2Overs(int overs) {
        powerPlay2Overs = overs;
    }

    // getters
    public int getPowerPlayOvers() {
        return powerPlayOvers;
    }

    public int getPowerPlay2Overs() {
        return powerPlay2Overs;
    }

    public int getOversPerInn() {
        return oversPerInnings;
    }
}
