import java.util.*;

public class KnapsackRefinedDP {

    public static void knapSack(int[] w, int[] p, int n, int W) {
        int[] P = new int[W + 1];
        int[][] keep = new int[n][W + 1];

        // Initialize profits to 0
        Arrays.fill(P, 0);

        // Build table P[] in bottom-up manner
        for (int i = 0; i < n; i++) {
            // We need to traverse weights from W to w[i] to avoid overwriting needed values
            for (int wgt = W; wgt >= w[i]; wgt--) {
                if (p[i] + P[wgt - w[i]] > P[wgt]) {
                    P[wgt] = p[i] + P[wgt - w[i]];
                    keep[i][wgt] = 1;
                } else {
                    keep[i][wgt] = 0;
                }
            }
            // For weights less than current item's weight
            for (int wgt = w[i] - 1; wgt >= 0; wgt--) {
                keep[i][wgt] = 0;
            }
        }

        // Backtracking to find the items included
        int K = W;
        List<Integer> items = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            if (keep[i][K] == 1) {
                items.add(i + 1); // Item numbers are 1-based
                K -= w[i];
            }
        }

        // Printing the results
        System.out.println("Maximum Profit: " + P[W]);
        System.out.print("Items included (item numbers): ");
        for (int i = items.size() - 1; i >= 0; i--) {
            System.out.print(items.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of items and capacity of knapsack
        System.out.print("Enter number of items (n): ");
        int n = scanner.nextInt();

        System.out.print("Enter capacity of knapsack (W): ");
        int W = scanner.nextInt();

        int[] w = new int[n]; // Weights
        int[] p = new int[n]; // Profits

        // Input weights and profits
        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Weight of item " + (i + 1) + ": ");
            w[i] = scanner.nextInt();
        }

        System.out.println("Enter profits of items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Profit of item " + (i + 1) + ": ");
            p[i] = scanner.nextInt();
        }

        // Solve the knapsack problem
        knapSack(w, p, n, W);

        scanner.close();
    }
}