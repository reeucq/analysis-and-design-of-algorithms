import java.util.Scanner;

/**
 * The LargestUniqueSubstring class finds the largest substring in a given string that 
 * contains all unique characters (no repeating characters).
 */
public class LargestUniqueSubstring {

    /**
     * The main method takes input from the user and prints the largest substring
     * with unique characters.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str = sc.next();
        System.out.println("Maximum length string having unique characters: " + maxUniqueSubString(str));

        sc.close();
    }

    /**
     * Finds the largest substring with all unique characters from the given string.
     *
     * @param str the input string
     * @return the largest substring without duplicate characters
     */
    static String maxUniqueSubString(String str) {
        int n = str.length();
        String ans = null;

        // Iterates over decreasing lengths of substrings (k) from the full length down to 1.
        found:
        for (int k = n; k >= 1; k--) {
            String[] substrings = KGrams(str, k);  // Get all substrings of length k
            for (String substring : substrings) {
                // If a substring with no duplicate characters is found, return it.
                if (!hasDuplicateChar(substring)) {
                    ans = substring;
                    break found;
                }
            }
        }
        return ans;
    }

    /**
     * Generates an array of all possible substrings (k-grams) of length k from the given string.
     *
     * @param str the input string
     * @param k the length of each substring
     * @return an array of all substrings of length k
     */
    static String[] KGrams(String str, int k) {
        int n = str.length();
        String[] kGrams = new String[n - k + 1];

        // Generate all possible substrings of length k
        for (int i = 0; i <= str.length() - k; i++) {
            kGrams[i] = str.substring(i, i + k);
        }
        return kGrams;
    }

    /**
     * Checks if the given string has duplicate characters.
     *
     * @param str the input string
     * @return true if the string contains duplicate characters, false otherwise
     */
    static boolean hasDuplicateChar(String str) {
        // Compare each character with every other character in the string
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;  // Duplicate character found
                }
            }
        }
        return false;  // No duplicates found
    }
}
