import java.util.Scanner;

public class BubblePartitionSort {

    /**
     * Reads 'n' integers from the user and stores them in array 'a'.
     *
     * @param a the array to store the integers
     * @param n the number of integers to read
     */
    public static void read(int a[], int n) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + n + " numbers:");
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close(); // Not closing to prevent closing System.in
    }

    /**
     * Prints the elements of array 'a'.
     *
     * @param a the array to print
     * @param n the number of elements in the array
     */
    public static void print(int a[], int n) {
        for(int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * Swaps two elements in array 'a' at indices 'i' and 'j'.
     *
     * @param a the array containing elements to swap
     * @param i the first index
     * @param j the second index
     */
    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Sorts the array 'a' such that even integers are in decreasing order
     * and odd integers are in increasing order using a modified bubble sort algorithm.
     *
     * @param a the array to sort
     * @param n the number of elements in the array
     */
    public static void bubblePartitionSort(int a[], int n) {
        boolean swapped;
        int swaps = 0; // Optional: To keep track of the number of swaps

        for(int i = 0; i < n; i++) {
            swapped = false;

            for(int j = 0; j < n - 1 - i; j++) {
                // Case 1: If current is odd and next is even, swap to move odd to the end
                if(isOdd(a[j]) && isEven(a[j + 1])) {
                    swap(a, j, j + 1);
                    swapped = true;
                    swaps++;
                }
                // Case 2: If both are even and current is smaller than next, swap for decreasing order
                else if(isEven(a[j]) && isEven(a[j + 1]) && a[j] < a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                    swaps++;
                }
                // Case 3: If both are odd and current is greater than next, swap for increasing order
                else if(isOdd(a[j]) && isOdd(a[j + 1]) && a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                    swaps++;
                }
            }

            // If no two elements were swapped in the inner loop, the array is sorted
            if(!swapped)
                break;
        }

        // Print the number of swaps made
        System.out.println("Total swaps made: " + swaps);
    }

    /**
     * Helper method to determine if a number is even.
     *
     * @param num the number to check
     * @return true if even, false otherwise
     */
    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    /**
     * Helper method to determine if a number is odd.
     *
     * @param num the number to check
     * @return true if odd, false otherwise
     */
    public static boolean isOdd(int num) {
        return num % 2 != 0;
    }

    /**
     * The main method to execute the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt user for the number of elements
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        // Initialize the array
        int[] a = new int[n];

        // Read the elements from the user
        read(a, n);

        // Print the original list
        System.out.println("Original list:");
        print(a, n);

        // Sort the array using bubblePartitionSort
        bubblePartitionSort(a, n);

        // Print the sorted list
        System.out.println("Sorted list:");
        print(a, n);

        sc.close();
    }
}