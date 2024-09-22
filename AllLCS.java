import java.util.Scanner;

public class AllLCS {

    private static char[] X;           // First input string as a character array
    private static char[] Y;           // Second input string as a character array
    private static int m, n;           // Lengths of the input strings
    private static int[][] c;          // DP table for LCS lengths
    private static int lcsLength;      // Length of the LCS
    private static char[] LCS;         // Array to store current LCS being built

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input strings
        System.out.print("Enter first string: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String s2 = scanner.nextLine();
        scanner.close();

        // Initialize variables
        X = s1.toCharArray();
        Y = s2.toCharArray();
        m = X.length;
        n = Y.length;

        // Compute the length of LCS
        computeLCSLength();

        // Check if there is at least one common subsequence
        if (lcsLength == 0) {
            System.out.println("No Common Subsequence found.");
            return;
        }

        System.out.println("Length of LCS is " + lcsLength);
        LCS = new char[lcsLength];

        System.out.println("All Longest Common Subsequences are:");
        // Start the recursive process with initial indices set to -1
        printAllLCS(-1, -1, -1);
    }

    /**
     * Computes the length of the Longest Common Subsequence (LCS)
     * using dynamic programming.
     */
    private static void computeLCSLength() {
        c = new int[m + 1][n + 1];

        // Build the LCS length table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (X[i - 1] == Y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }

        lcsLength = c[m][n];
    }

    /**
     * Recursively prints all LCSs based on the provided algorithm.
     *
     * @param i Current index in the first string
     * @param j Current index in the second string
     * @param k Current position in the LCS array
     */
    private static void printAllLCS(int i, int j, int k) {
        if (isPromising(i, j, k)) {
            if (k == lcsLength - 1) {
                // LCS is complete; print it
                for (int a = 0; a < lcsLength; a++) {
                    System.out.print(LCS[a]);
                }
                System.out.println();
            } else {
                // Explore further characters to build LCS
                for (int ii = i + 1; ii < m; ii++) {
                    for (int jj = j + 1; jj < n; jj++) {
                        if (X[ii] == Y[jj]) {
                            LCS[k + 1] = X[ii];
                            printAllLCS(ii, jj, k + 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the current state is promising for building an LCS.
     *
     * @param i Current index in the first string
     * @param j Current index in the second string
     * @param k Current position in the LCS array
     * @return true if promising, false otherwise
     */
    private static boolean isPromising(int i, int j, int k) {
        if (k == lcsLength - 1) {
            return true;
        }

        // Calculate the remaining characters in both strings
        int remainingX = m - (i + 1);
        int remainingY = n - (j + 1);
        int required = lcsLength - (k + 1);

        // Check if there are enough characters left to build an LCS
        if (remainingX < required || remainingY < required) {
            return false;
        }

        return true;
    }
}