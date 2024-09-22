import java.util.HashSet;

public class DuplicateCharChecker {

    /**
     * Determines if the input string has duplicate characters.
     *
     * @param s the string to check
     * @return true if duplicates exist, false otherwise
     */
    public static boolean hasDuplicateChars(String s) {
        HashSet<Character> charSet = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (charSet.contains(c)) {
                // Duplicate found
                return true;
            }
            charSet.add(c);
        }

        // No duplicates found
        return false;
    }

    // Optional: Main method for testing
    public static void main(String[] args) {
        String test1 = "hello";
        String test2 = "world";
        
        System.out.println("Does \"" + test1 + "\" have duplicates? " + hasDuplicateChars(test1));
        System.out.println("Does \"" + test2 + "\" have duplicates? " + hasDuplicateChars(test2));
    }
}