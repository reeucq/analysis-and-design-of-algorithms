import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MST {

    // Disjoint Set Data Structure
    static int[] u; // Union-Find array for Disjoint Sets

    // Graph representation for Kruskal's Algorithm
    static class Edge {
        int src, dest, weight;
    }

    static class Graph {
        int V, E; // Number of vertices and edges
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[e];
            for (int i = 0; i < e; ++i)
                edges[i] = new Edge();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();

        // Create a graph
        Graph graph = new Graph(V, E);

        System.out.println("Enter the edges in the format: src dest weight");
        for (int i = 0; i < E; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            graph.edges[i].src = sc.nextInt() - 1; // Adjusting for 0-based indexing
            graph.edges[i].dest = sc.nextInt() - 1;
            graph.edges[i].weight = sc.nextInt();
        }

        // Initialize the adjacency matrix for Prim's algorithm
        int[][] W = new int[V][V];
        for (int i = 0; i < V; i++)
            Arrays.fill(W[i], Integer.MAX_VALUE); // Initialize weights to infinity

        for (Edge edge : graph.edges) {
            W[edge.src][edge.dest] = edge.weight;
            W[edge.dest][edge.src] = edge.weight; // For undirected graph
        }

        sc.close();

        // Kruskal's Algorithm
        System.out.println("\nKruskal's Algorithm:");
        KruskalMST(graph);

        // Prim's Algorithm
        System.out.println("\nPrim's Algorithm:");
        PrimMST(W, V);

        // Comparison
        System.out.println("\nComparison between Kruskal's and Prim's algorithms:");
        compareAlgorithms();
    }

    // ---------------- Disjoint Set Methods ----------------

    // makeset method
    static void makeset(int n) {
        u = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = i;
        }
    }

    // find method
    static int find(int[] u, int i) {
        int j = i;
        while (u[j] != j) {
            j = u[j];
        }
        return j;
    }

    // merge method
    static void merge(int p, int q) {
        if (q > p) {
            u[q] = p;
        } else {
            u[p] = q;
        }
    }

    // equal method
    static boolean equal(int p, int q) {
        return p == q;
    }

    // ---------------- Kruskal's Algorithm ----------------

    static void KruskalMST(Graph graph) {
        int V = graph.V;
        Edge[] result = new Edge[V]; // Tnis will store the resultant MST
        int e = 0; // Index used in result[]
        int i; // Index used for sorted edges

        // Step 1: Sort all the edges in non-decreasing order of their weight
        Arrays.sort(graph.edges, Comparator.comparingInt(o -> o.weight));

        // Allocate memory for disjoint sets
        makeset(V);

        i = 0; // Index for sorted edges
        while (e < V - 1 && i < graph.E) {
            // Step 2: Pick the smallest edge and increment the index for next iteration
            Edge nextEdge = graph.edges[i++];
            int x = find(u, nextEdge.src);
            int y = find(u, nextEdge.dest);

            // If including this edge doesn't cause cycle, include it in result
            if (!equal(x, y)) {
                result[e++] = nextEdge;
                merge(x, y);
            }
            // Else discard the edge
        }

        // Print the constructed MST
        System.out.println("Edges in the MST:");
        int totalWeight = 0;
        for (i = 0; i < e; ++i) {
            System.out.println((result[i].src + 1) + " -- " + (result[i].dest + 1) + " == " + result[i].weight);
            totalWeight += result[i].weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    // ---------------- Prim's Algorithm ----------------

    static void PrimMST(int[][] W, int n) {
        int[] nearest = new int[n];
        int[] distance = new int[n];
        boolean[] inTree = new boolean[n];
        ArrayList<String> mstEdges = new ArrayList<>();

        // Initialize
        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            distance[i] = W[0][i];
        }
        inTree[0] = true; // Include first vertex in MST

        int totalWeight = 0;

        for (int iter = 0; iter < n - 1; iter++) {
            int min = Integer.MAX_VALUE;
            int near = -1;

            // Find the closest vertex not in the tree
            for (int i = 1; i < n; i++) {
                if (!inTree[i] && distance[i] < min) {
                    min = distance[i];
                    near = i;
                }
            }

            if (near == -1) {
                System.out.println("Graph is not connected.");
                return;
            }

            // Include this vertex in the tree
            inTree[near] = true;
            totalWeight += distance[near];
            mstEdges.add((nearest[near] + 1) + " -- " + (near + 1) + " == " + distance[near]);

            // Update the distances
            for (int i = 1; i < n; i++) {
                if (!inTree[i] && W[near][i] < distance[i]) {
                    distance[i] = W[near][i];
                    nearest[i] = near;
                }
            }
        }

        // Print the constructed MST
        System.out.println("Edges in the MST:");
        for (String edge : mstEdges) {
            System.out.println(edge);
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    // ---------------- Comparison ----------------

    static void compareAlgorithms() {
        System.out.println("Kruskal's Algorithm:");
        System.out.println("- Works on disconnected graphs (finds the MST of each connected component).");
        System.out.println("- Uses Disjoint Set data structure to detect cycles.");
        System.out.println("- Better suited for sparse graphs.");

        System.out.println("\nPrim's Algorithm:");
        System.out.println("- Requires the graph to be connected.");
        System.out.println("- Builds the MST by always adding the nearest vertex to the tree.");
        System.out.println("- Better suited for dense graphs.");

        System.out.println("\nCommonalities:");
        System.out.println("- Both are greedy algorithms used to find the Minimum Spanning Tree of a graph.");
        System.out.println("- Both aim to minimize the total weight of the edges in the tree.");
    }
}