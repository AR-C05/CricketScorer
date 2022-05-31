package data;

/**
 * Name class. Holds data for player name.
 * <p>
 * Date Created: 2022-05-29
 * <p>
 * Date Updated: 2022-05-29
 * 
 * @author AR.C
 */
public class Name {
    private String firstName = " ", middleName = " ", lastName = " ";

    /**
     * Constructor; Full name - first, middle, last
     * @param fName
     * @param mName
     * @param lName
     */
    public Name (String fName, String mName, String lName) {
        firstName = fName;
        middleName = mName;
        lastName = lName;
    }
    
    public Name (String fName, String lName) {
        firstName = fName;
        lastName = lName;
    }

    public Name (String fName) {
        firstName = fName;
    }

    public String toString() {
        return (firstName + " " + middleName + " " + lastName).strip();
    }
}
