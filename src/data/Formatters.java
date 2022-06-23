package data;

public class Formatters {

    public static String toTitleCase(String s) {
        if (s == null)
            return "";
        String finalStr;
        s = s.toLowerCase().strip();
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ' ')
                letters[i + 1] = Character.toUpperCase(letters[i + 1]);
        }
        finalStr = new String(letters);
        return finalStr;
    }
    
}
