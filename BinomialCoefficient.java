import java.util.Arrays;

public class BinomialCoefficient {

    // Method 1: Normal method using for loops
    public static long binomialCoefficientNormal(int n, int k) {
        if (k > n - k) { // Take advantage of symmetry
            k = n - k;
        }

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= k; i++) {
            numerator *= (n - i + 1);
            denominator *= i;
        }

        return numerator / denominator;
    }

    // Method 2: Using Recursion
    public static long binomialCoefficientRecursive(int n, int k) {
        // Base cases
        if (k == 0 || k == n) {
            return 1;
        }
        // Recursive relation
        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    // Method 3: Using Dynamic Programming (2D Array)
    public static long binomialCoefficientDP2D(int n, int k) {
        long[][] C = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // Calculate value using previously stored values
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        return C[n][k];
    }

    // Method 4: Using Dynamic Programming (Optimized 2D Array)
    public static long binomialCoefficientDPOptimized2D(int n, int k) {
        // Using a jagged 2D array where each row i has (i+1) elements
        long[][] C = new long[n + 1][];

        for (int i = 0; i <= n; i++) {
            C[i] = new long[Math.min(i, k) + 1];
            for (int j = 0; j <= Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // Calculate value using previously stored values
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        if (k > n) {
            return 0;
        }
        return C[n][k];
    }

    // Method 5: Using Dynamic Programming (1D Array)
    public static long binomialCoefficientDP1D(int n, int k) {
        long[] C = new long[k + 1];
        Arrays.fill(C, 0);
        C[0] = 1; // C(n, 0) is always 1

        for (int i = 1; i <= n; i++) {
            // Compute next row of pascal triangle using the previous row
            for (int j = Math.min(i, k); j > 0; j--) {
                C[j] += C[j - 1];
            }
        }

        return C[k];
    }

    // Main method to demonstrate all methods
    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        System.out.println("Binomial Coefficient C(" + n + ", " + k + "):\n");

        // Method 1: Normal Method
        long resultNormal = binomialCoefficientNormal(n, k);
        System.out.println("1. Normal Method (For Loops): " + resultNormal);

        // Method 2: Recursion
        long resultRecursive = binomialCoefficientRecursive(n, k);
        System.out.println("2. Recursion: " + resultRecursive);

        // Method 3: Dynamic Programming (2D Array)
        long resultDP2D = binomialCoefficientDP2D(n, k);
        System.out.println("3. Dynamic Programming (2D Array): " + resultDP2D);

        // Method 4: Dynamic Programming (Optimized 2D Array)
        long resultDPOptimized2D = binomialCoefficientDPOptimized2D(n, k);
        System.out.println("4. Dynamic Programming (Optimized 2D Array): " + resultDPOptimized2D);

        // Method 5: Dynamic Programming (1D Array)
        long resultDP1D = binomialCoefficientDP1D(n, k);
        System.out.println("5. Dynamic Programming (1D Array): " + resultDP1D);
    }
}