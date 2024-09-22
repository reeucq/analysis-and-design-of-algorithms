import java.util.Scanner;
import java.util.Arrays;

/**
 * The Factorial class calculates the factorial of a given number and returns 
 * the result as an array representing the digits of the large factorial number.
 */
class Factorial {

    /**
     * Calculates the factorial of a given number and returns the result as an array of digits.
     * This method efficiently computes large factorials by storing the digits in an array and
     * using logarithms to determine the number of digits required.
     *
     * @param n the number whose factorial is to be calculated
     * @return an integer array representing the digits of the factorial result
     */
    public static int[] factorial(int n) {
        // Sum of logarithms to calculate the number of digits in the factorial result
        double sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += Math.log10(i);
        }
        
        // m represents the total number of digits in the result
        int m = (int) Math.floor(sum) + 1;
        int[] f = new int[m]; // Array to hold the digits of the factorial
        int j = m - 1;        // Index to track the significant digits in the array
        f[m - 1] = 1;         // Initialize the least significant digit to 1 (as factorial(1) = 1)
        int mul = 0;          // Counter to track the number of multiplications performed

        // Outer loop to multiply numbers from 2 to n
        for (int i = 2; i <= n; i++) {
            int c = 0;        // Carry for the multiplication process
            // Inner loop to perform the multiplication of each digit by i
            for (int k = m - 1; k >= j; k--) {
                int x = i * f[k] + c;  // Multiply each digit by i and add the carry
                f[k] = x % 10;         // Store the last digit of the result
                c = x / 10;            // Update carry with the remaining digits
                mul++;                 // Increment multiplication counter
            }

            // Process remaining carry and adjust the array index (j)
            while (c != 0) {
                j = j - 1;
                f[j] = c % 10;  // Store the carry digits
                c = c / 10;
            }
        }
        System.out.println("No. of Multiplications: " + mul);  // Print the number of multiplications
        return f;  // Return the array containing the digits of the factorial
    }

    /**
     * The main method takes input from the user and prints the factorial result.
     * It prompts the user to enter a number, computes the factorial using the `factorial` method,
     * and displays the result as an array of digits.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();           // Read the input number
        int[] f = factorial(n);         // Calculate the factorial
        System.out.println(Arrays.toString(f));  // Display the result as an array of digits
        sc.close();
    }
}
