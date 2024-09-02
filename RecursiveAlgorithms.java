/**
 * RecursiveAlgorithms.java
 * This class contains recursive algorithms mentioned in Unit-1 of JMI MCA
 * CA-31 - Analysis & Design of Algorithms Course.
 * 
 * The following algorithms are implemented in this class:
 * 1. factorial(int n) - to print the factorial of a number.
 * 2. GCD(int a, int b) - to find the Greatest Common Divisor of two numbers.
 * 3. fibonacci(int n) - to print the nth Fibonacci number.
 * 4. per(a[], k, n) - to print all permutations of an array.
 */
public class RecursiveAlgorithms {
    /**
     * This method calculates the factorial of a number using recursion.
     * 
     * @param n - the number whose factorial is to be calculated.
     * @return the factorial of the number.
     * 
     *         Time Complexity: O(n)
     *         Space Complexity: O(n)
     *         Explanation: The method makes n recursive calls, each taking constant
     *         time.
     *         The space complexity is O(n) due to the call stack depth in
     *         recursion.
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * This method calculates the Greatest Common Divisor of two numbers using
     * recursion.
     * The Euclidean algorithm is based on the principle that the GCD of two numbers
     * also divides their difference. The algorithm repeatedly replaces the larger
     * number by its remainder when divided by the smaller number until one of the
     * numbers becomes zero. At that point, the non-zero number is the GCD.
     * 
     * @param m - the first number.
     * @param n - the second number.
     * @return the GCD of the two numbers.
     * 
     *         Time Complexity: O(log(min(m,n)))
     *         Space Complexity: O(log(min(m,n)))
     *         Explanation: The Euclidean algorithm reduces the problem size
     *         logarithmically.
     *         The space complexity is due to the recursive call stack.
     */
    public static int gcd(int m, int n) {
        if (m % n == 0) {
            return n;
        } else {
            return gcd(n, m % n);
        }
    }

    /**
     * This method calculates the nth Fibonacci number using recursion.
     * The Fibonacci sequence is a series of numbers in which each number is the sum
     * of the two preceding ones, usually starting with 0 and 1.
     * 
     * @param n - the position of the Fibonacci number to be calculated.
     * @return the nth Fibonacci number.
     * 
     *         Time Complexity: O(2^n)
     *         Space Complexity: O(n)
     *         Explanation: Each call branches into two recursive calls, leading to
     *         exponential time.
     *         The space complexity is O(n) due to the maximum depth of the
     *         recursion tree.
     */
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * This method calculates all permutations of an array using recursion.
     * It does so by swapping the elements of the array and recursively calling the
     * method to calculate the permutations.
     * 
     * 
     * @param a - the array whose permutations are to be calculated.
     * @param k - the starting index of the array.
     * @param n - the ending index of the array.
     * 
     *          Time Complexity: O(n!)
     *          Space Complexity: O(n)
     *          Explanation: The method generates all n! permutations of n elements.
     *          The space complexity is O(n) due to the recursive call stack and the
     *          array size.
     */
    public static void per(int a[], int k, int n) {
        if (k == n) {
            for (int i = 0; i <= n; i++) {
                System.out.print(a[i]);
            }
            System.out.println();
        } else {
            for (int i = k; i <= n; i++) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                per(a, k + 1, n);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        // Test the factorial method
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Factorial of 6: " + factorial(6));

        // Test the GCD method
        System.out.println("GCD of 12 and 18: " + gcd(12, 18));
        System.out.println("GCD of 15 and 25: " + gcd(15, 25));

        // Test the Fibonacci method
        System.out.println("Fibonacci number at position 5: " + fib(5));
        System.out.println("Fibonacci number at position 6: " + fib(6));

        // Test the permutations method
        int a[] = { 1, 2, 3 };
        per(a, 0, a.length - 1);
    }
}
