import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphIsomorphismFinder {
    // Global variables for pattern and data graphs
    private static int[][] pMatrix; // Adjacency matrix of Gp
    private static int[][] dMatrix; // Adjacency matrix of Gd
    private static String[] pLabels; // Labels of Gp vertices
    private static String[] dLabels; // Labels of Gd vertices
    private static boolean hasLabels; // Flag for labeled graphs

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Graph Isomorphism Finder using Backtracking");

        // Determine if graphs have labels
        System.out.print("Do the graphs have vertex labels? (y/n): ");
        String labelChoice = scanner.nextLine().trim().toLowerCase();
        hasLabels = labelChoice.equals("y");

        // Read Pattern Graph Gp
        System.out.println("\n--- Pattern Graph Gp ---");
        System.out.print("Enter number of vertices in Gp: ");
        int m = Integer.parseInt(scanner.nextLine().trim());
        pMatrix = readAdjacencyMatrix(scanner, m, "Gp");
        if (hasLabels) {
            pLabels = readLabels(scanner, m, "Gp");
        }

        // Read Data Graph Gd
        System.out.println("\n--- Data Graph Gd ---");
        System.out.print("Enter number of vertices in Gd: ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        dMatrix = readAdjacencyMatrix(scanner, n, "Gd");
        if (hasLabels) {
            dLabels = readLabels(scanner, n, "Gd");
        }

        // Find Induced Isomorphic Images
        System.out.println("\nFinding Induced Isomorphic Images...");
        List<int[]> inducedEmbeddings = findIsomorphicSubgraphs(m, n, true);
        if (!inducedEmbeddings.isEmpty()) {
            System.out.println("Total Induced Isomorphic Embeddings Found: " + inducedEmbeddings.size());
            printEmbeddings(inducedEmbeddings);
        } else {
            System.out.println("No Induced Isomorphic Embeddings Found.");
        }

        // Find Non-Induced Isomorphic Images
        System.out.println("\nFinding Non-Induced Isomorphic Images...");
        List<int[]> nonInducedEmbeddings = findIsomorphicSubgraphs(m, n, false);
        if (!nonInducedEmbeddings.isEmpty()) {
            System.out.println("Total Non-Induced Isomorphic Embeddings Found: " + nonInducedEmbeddings.size());
            printEmbeddings(nonInducedEmbeddings);
        } else {
            System.out.println("No Non-Induced Isomorphic Embeddings Found.");
        }

        scanner.close();
    }

    /**
     * Reads the adjacency matrix for a graph.
     *
     * @param scanner Scanner object for input
     * @param size    Number of vertices
     * @param graphName Name of the graph (Gp or Gd) for prompts
     * @return Adjacency matrix as a 2D array
     */
    private static int[][] readAdjacencyMatrix(Scanner scanner, int size, String graphName) {
        int[][] matrix = new int[size][size];
        System.out.println("Enter adjacency matrix row by row (space-separated) for graph " + graphName + ":");
        for (int i = 0; i < size; i++) {
            while (true) {
                System.out.print("Row " + (i + 1) + ": ");
                String[] tokens = scanner.nextLine().trim().split("\\s+");
                if (tokens.length != size) {
                    System.out.println("Please enter exactly " + size + " values.");
                    continue;
                }
                try {
                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = Integer.parseInt(tokens[j]);
                        if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                            throw new NumberFormatException();
                        }
                    }
                    break; // Valid row entered
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter only 0s and 1s.");
                }
            }
        }
        return matrix;
    }

    /**
     * Reads vertex labels for a graph.
     *
     * @param scanner Scanner object for input
     * @param size    Number of vertices
     * @param graphName Name of the graph (Gp or Gd) for prompts
     * @return Array of labels
     */
    private static String[] readLabels(Scanner scanner, int size, String graphName) {
        String[] labels = new String[size];
        while (true) {
            System.out.print("Enter labels for the " + size + " vertices of " + graphName + " (space-separated): ");
            String[] tokens = scanner.nextLine().trim().split("\\s+");
            if (tokens.length != size) {
                System.out.println("Number of labels does not match number of vertices. Try again.");
                continue;
            }
            for (int i = 0; i < size; i++) {
                labels[i] = tokens[i];
            }
            break;
        }
        return labels;
    }

    /**
     * Finds all isomorphic subgraphs of Gp in Gd based on the induced flag.
     *
     * @param m        Number of vertices in Gp
     * @param n        Number of vertices in Gd
     * @param induced  True for induced isomorphism, False otherwise
     * @return List of embeddings, each embedding is an array mapping Gp vertices to Gd vertices
     */
    private static List<int[]> findIsomorphicSubgraphs(int m, int n, boolean induced) {
        List<int[]> results = new ArrayList<>();
        int[] em = new int[m]; // Embedding array

        // Iterate through all possible initial mappings
        for (int j = 0; j < n; j++) {
            // If labels are present, ensure first vertex labels match
            if (hasLabels && !pLabels[0].equals(dLabels[j])) {
                continue;
            }
            em[0] = j;
            getEmbeddings(0, m, n, em, results, induced);
        }

        return results;
    }

    /**
     * Recursive backtracking function to find embeddings.
     *
     * @param i       Current index in Gp
     * @param m       Total number of vertices in Gp
     * @param n       Total number of vertices in Gd
     * @param em      Current embedding array
     * @param results List to store successful embeddings
     * @param induced True for induced isomorphism, False otherwise
     */
    private static void getEmbeddings(int i, int m, int n, int[] em, List<int[]> results, boolean induced) {
        if (isPromising(i, m, n, em, induced)) {
            if (i == m - 1) {
                // Found a complete embedding
                results.add(em.clone());
            } else {
                // Try mapping the next vertex in Gp
                for (int j = 0; j < n; j++) {
                    em[i + 1] = j;
                    getEmbeddings(i + 1, m, n, em, results, induced);
                }
            }
        }
    }

    /**
     * Checks if the current partial embedding is promising.
     *
     * @param i       Current index in Gp
     * @param m       Total number of vertices in Gp
     * @param n       Total number of vertices in Gd
     * @param em      Current embedding array
     * @param induced True for induced isomorphism, False otherwise
     * @return True if promising, False otherwise
     */
    private static boolean isPromising(int i, int m, int n, int[] em, boolean induced) {
        boolean promising = true;
        for (int k = 0; k < i; k++) {
            // Check for injective mapping
            if (em[k] == em[i]) {
                promising = false;
                break;
            }

            // Check adjacency constraints
            if (pMatrix[i][k] != dMatrix[em[i]][em[k]]) {
                if (induced) {
                    promising = false;
                    break;
                } else {
                    // For non-induced: if p has edge, d must have edge; else, ignore
                    if (pMatrix[i][k] == 1 && dMatrix[em[i]][em[k]] == 0) {
                        promising = false;
                        break;
                    }
                }
            }

            // For induced isomorphism, ensure that non-edges in Gp are also non-edges in Gd
            if (induced) {
                if (pMatrix[i][k] == 0 && dMatrix[em[i]][em[k]] == 1) {
                    promising = false;
                    break;
                }
            }
        }
        return promising;
    }

    /**
     * Prints all the embeddings in a readable format.
     *
     * @param embeddings List of embeddings
     */
    private static void printEmbeddings(List<int[]> embeddings) {
        int count = 1;
        for (int[] em : embeddings) {
            System.out.print("Embedding " + count + ": ");
            List<String> mappings = new ArrayList<>();
            for (int i = 0; i < em.length; i++) {
                String pVertex = hasLabels ? pLabels[i] : "P" + i;
                String dVertex = hasLabels ? dLabels[em[i]] : "D" + em[i];
                mappings.add(pVertex + " -> " + dVertex);
            }
            System.out.println(String.join(", ", mappings));
            count++;
        }
    }
}