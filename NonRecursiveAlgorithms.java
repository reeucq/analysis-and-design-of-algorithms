/**
 * NonRecursiveAlgorithms.java
 * This class contains non-recursive algorithms mentioned in Unit-1 of JMI MCA
 * CA-31 - Analysis & Design of Algorithms Course.
 * 
 * The following algorithms are implemented in this class:
 * 1. isPrime(int n) - This method checks whether a given number is prime or
 * not.
 * 2. truthTable(int n) - This method generates the truth table of n boolean
 * variables.
 * 3. decimalToBinary(int d) - This method converts a decimal number to binary.
 * 4. selectionSort(a[1..n], n) - This method sorts an array of n elements using
 * selection sort.
 * 5. sequentialSearch(a[1..n], n, x) - This method searches for an element x in
 * an array of n elements using sequential search.
 * 6. hornerRule(a[0..n], n, x) - This method evaluates a polynomial of degree n
 * using Horner's Rule.
 */
public class NonRecursiveAlgorithms {
    /**
     * This method checks whether a given number is prime or not.
     * It does so by checking whether the number is divisible by any number from 2
     * to sqrt(n).
     * If the number is divisible by any number in this range, then it is not prime.
     * Checking is only needed until sqrt(n) because if n can be factored into two
     * factors, then at least one of them must be less than or equal to sqrt(n), if
     * both are greater than sqrt(n), then their product would be greater than n.
     * 
     * @param n - number to be checked for primality
     * @return boolean
     * 
     *         Time Complexity: O(sqrt(n))
     *         Space Complexity: O(1)
     *         Explanation: The method checks divisibility up to the square root of
     *         n. It uses a constant amount of extra space, regardless of the input
     *         size.
     */
    public static boolean isPrime(int n) {
        int m = (int) Math.sqrt(n);
        boolean isPrime = true;
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * This method generates the truth table of n boolean variables.
     * It initializes an array 'a' of size n with all values set to False (F).
     * The algorithm counts from 0 to 2^n - 1 (where n is the number of variables).
     * Each of these numbers, when represented in binary, gives us a unique
     * combination of 0s and 1s.
     * In the algorithm, 0 is mapped to False (F) and 1 is mapped to True (T).
     * The while loop (steps 6-10) is actually converting each decimal number to its
     * binary representation. It does this by: Checking if the number is odd or even
     * (m % 2), Dividing the number by 2 repeatedly (m = floor(m/2))
     * As it converts to binary, it fills the array 'a' from right to left (that's
     * why j starts at n and decreases).
     * 
     * @param n - number of boolean variables
     * 
     *          Time Complexity: O(2^n)
     *          Space Complexity: O(n)
     *          Explanation: It generates all possible combinations of n boolean
     *          variables, which is 2^n. It uses an array of size n to store the
     *          current combination.
     */
    public static void truthTable(int n) {
        boolean[] a = new boolean[n];

        for (int i = 0; i < n; i++)
            a[i] = false;

        for (int i = 0; i < Math.pow(2, n); i++) {
            int m = i;
            int j = n;

            while (m != 0) {
                a[--j] = m % 2 == 1;
                m /= 2;
            }

            for (int k = 0; k < n; k++) {
                System.out.print(a[k] ? "T" : "F");
            }

            System.out.println();
        }
    }

    /**
     * This method converts a decimal number to binary.
     * It does so by repeatedly dividing the decimal number by 2 and storing the
     * remainder.
     * Inside the loop, the code updates b using b = b + (d % 2) * p; to calculate
     * the binary representation and p using p = 10 * p; to handle the place value
     * in binary form.
     * The variable d is updated using integer division by 2 with d = d / 2;.
     * The loop continues until d is not equal to 0.
     * 
     * @param d - decimal number to be converted to binary
     * @return b - binary representation of the decimal number
     * 
     *         Time Complexity: O(log d)
     *         Space Complexity: O(1)
     *         Explanation: The number of iterations is proportional to the number
     *         of digits in the binary representation, which is log d. It uses a
     *         constant amount of extra space.
     */
    public static int decimalToBinary(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("d must be a positive integer.");
        }

        int b = 0;
        int p = 1;

        while (d != 0) {
            b = b + (d % 2) * p;
            d = d / 2;
            p = p * 10;
        }

        return b;
    }

    /**
     * In selection sort, we pick the smallest element and swap it with the first
     * element. Then we pick the second smallest element and swap it with the second
     * element, and so on. We repeat this process until the entire array is sorted.
     * 
     * The no. of comparisons in selection sort is always n(n-1)/2, where n is the
     * number of elements in the array.
     * (n-1) in the first pass, (n-2) in the second pass, (n-3) in the third pass up
     * until 1 comparison in the last pass.
     * 
     * @param a - array of elements
     * @param n - number of elements in the array
     * 
     *          Time Complexity: O(n^2)
     *          Space Complexity: O(1)
     *          Explanation: It uses nested loops, resulting in n(n-1)/2
     *          comparisons. It sorts in-place, using only a constant amount of
     *          extra space.
     */
    public static void selectionSort(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    /**
     * This method searches for an element x in an array of n elements using
     * sequential search.
     * It does so by iterating over the array and checking if the element at index i
     * is equal to x.
     * If it is, then it returns the index i.
     * If the loop completes without finding x, then it returns -1.
     * 
     * @param a - array of elements
     * @param n - number of elements in the array
     * @param x - element to be searched
     * @return index of x in the array, -1 if x is not found
     * 
     *         Time Complexity: O(n)
     *         Space Complexity: O(1)
     *         Explanation: In the worst case, it examines every element once. It
     *         uses a constant amount of extra space, regardless of the input size.
     */
    public static int sequentialSearch(int[] a, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (a[i] == x) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This method evaluates a polynomial of degree n using Horner's Rule.
     * Horner's Rule is a method for evaluating polynomials in a way that reduces
     * the
     * number of multiplications.
     * 
     * The algorithm starts by initializing the result to 0.
     * It then iterates over the array of coefficients a from the highest degree to
     * the lowest degree.
     * 
     * for ex: if the polynomial is a0 + a1*x + a2*x^2 + a3*x^3 + ... + an*x^n
     * then the algorithm calculates the result as:
     * a0 + x(a1 + x(a2 + x(a3 + ... + x(an-1 + x*an)...)))
     * 
     * let the polynomial be 2x^3 + 3x^2 - 4x + 5
     * then the array of coefficients would be [5, -4, 3, 2]
     * Initialize: Result = 0
     * For loop starts: i = 3 to 1, step -1
     * i = 3:
     * Result = (0 + 2) * 2 = 4
     * i = 2:
     * Result = (4 + 3) * 2 = 14
     * i = 1:
     * Result = (14 + (-4)) * 2 = 20
     * After the loop:
     * Result = 20 + 5 = 25
     * Return 25
     * 
     * Verification: 2(2^3) + 3(2^2) - 4(2) + 5 = 16 + 12 - 8 + 5 = 25
     * 
     * @param a  - array of coefficients of the polynomial
     * @param n  - degree of the polynomial
     * @param x0 - value of x for which the polynomial is to be evaluated
     * @return result of the polynomial
     * 
     *         Time Complexity: O(n)
     *         Space Complexity: O(1)
     *         Explanation: It iterates through the array once, performing a
     *         constant number of operations per element. It uses a constant amount
     *         of extra space.
     */
    public static double horner(double[] a, int n, double x0) {
        double result = 0;

        for (int i = n; i >= 1; i--) {
            result = (result + a[i]) * x0;
        }

        result += a[0];
        return result;
    }

    public static void main(String[] args) {
        // Test isPrime
        System.out.println("isPrime(7): " + isPrime(7));
        System.out.println("isPrime(8): " + isPrime(8));
        System.out.println("isPrime(11): " + isPrime(11));
        System.out.println("isPrime(12): " + isPrime(12));

        // Test truthTable
        System.out.println("truthTable(2): ");
        truthTable(2);

        System.out.println("truthTable(3): ");
        truthTable(3);

        // Test decimalToBinary
        System.out.println("decimalToBinary(10): " + decimalToBinary(10));
        System.out.println("decimalToBinary(15): " + decimalToBinary(15));
        System.out.println("decimalToBinary(20): " + decimalToBinary(20));
        System.out.println("decimalToBinary(50): " + decimalToBinary(50));

        // Test selectionSort
        int[] a = { 64, 25, 12, 22, 11 };
        selectionSort(a, a.length);
        System.out.println("selectionSort([64, 25, 12, 22, 11]): " + java.util.Arrays.toString(a));

        int[] b = { 64, 25, 12, 22, 11, 10, 5, 3, 1, 0 };
        selectionSort(b, b.length);
        System.out.println("selectionSort([64, 25, 12, 22, 11, 10, 5, 3, 1, 0]): " + java.util.Arrays.toString(b));

        // Test sequentialSearch
        int[] c = { 64, 25, 12, 22, 11 };
        System.out.println("sequentialSearch([64, 25, 12, 22, 11], 5, 12): " + sequentialSearch(c, c.length, 12));

        int[] d = { 64, 25, 12, 22, 11, 10, 5, 3, 1, 0 };
        System.out.println("sequentialSearch([64, 25, 12, 22, 11, 10, 5, 3, 1, 0], 10, 7): "
                + sequentialSearch(d, d.length, 7));

        // Test horner
        double[] e = { 5, -4, 3, 2 };
        System.out.println("horner([5, -4, 3, 2], 3, 2): " + horner(e, 3, 2));
    }
}
