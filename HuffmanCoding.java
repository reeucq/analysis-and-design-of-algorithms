import java.util.*;

public class HuffmanCoding {

    // Priority Queue Class
    static class PriorityQueue {
        private List<Node> queue;
    
        public PriorityQueue() {
            this.queue = new ArrayList<>();
        }
    
        // Insert node into the queue maintaining the order based on frequency
        public void insert(Node node) {
            if (queue.isEmpty()) {
                queue.add(node);
            } else {
                int i = 0;
                while (i < queue.size() && queue.get(i).frequency < node.frequency) {
                    i++;
                }
                queue.add(i, node);
            }
        }
    
        // Remove and return the node with the smallest frequency
        public Node remove() throws Exception {
            if (!isEmpty()) {
                return queue.remove(0);
            } else {
                throw new Exception("Priority Queue is empty.");
            }
        }
    
        // Check if the queue is empty
        public boolean isEmpty() {
            return queue.isEmpty();
        }
    
        // For debugging: Display the current state of the queue
        public void display() {
            for (Node node : queue) {
                if (node.isLeaf()) {
                    System.out.print(node.symbol + ":" + node.frequency + " ");
                } else {
                    System.out.print("Internal:" + node.frequency + " ");
                }
            }
            System.out.println();
        }
    }

    // Node Class
    static class Node {
        char symbol;      // Symbol (e.g., 'a', 'b', etc.)
        int frequency;    // Frequency of the symbol
        Node left;        // Left child
        Node right;       // Right child
    
        // Constructor for leaf nodes
        public Node(char symbol, int frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
    
        // Constructor for internal nodes
        public Node(int frequency) {
            this.symbol = '\0'; // Null character for internal nodes
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
    
        // Check if the node is a leaf
        public boolean isLeaf() {
            return (this.left == null) && (this.right == null);
        }
    }

    // Function to generate Huffman Codes by traversing the tree
    public static void generateHuffmanCodes(Node node, String currentCode, Map<Character, String> codes) {
        if (node == null) {
            return;
        }

        // If this is a leaf node, it contains one of the input symbols
        if (node.isLeaf()) {
            codes.put(node.symbol, currentCode.length() > 0 ? currentCode : "0"); // Handle single symbol case
            return;
        }

        // Traverse the left subtree
        generateHuffmanCodes(node.left, currentCode + "0", codes);

        // Traverse the right subtree
        generateHuffmanCodes(node.right, currentCode + "1", codes);
    }

    // Huffman Coding Algorithm
    public static Map<Character, String> huffmanCoding(char[] symbols, int[] frequencies) throws Exception {
        int n = symbols.length;
        if (n != frequencies.length) {
            throw new IllegalArgumentException("The length of symbols and frequencies must be equal.");
        }

        // Initialize priority queue
        PriorityQueue pq = new PriorityQueue();

        // Insert all symbols into the priority queue
        for (int i = 0; i < n; i++) {
            Node node = new Node(symbols[i], frequencies[i]);
            pq.insert(node);
            System.out.println("Inserted into PQ: " + node.symbol + " with frequency " + node.frequency);
        }

        System.out.print("\nInitial Priority Queue: ");
        pq.display();

        // Construct the Huffman Tree
        for (int i = 0; i < n - 1; i++) {
            // Remove two nodes with the smallest frequencies
            Node p = pq.remove();
            Node q = pq.remove();
            System.out.println("\nRemoved from PQ: " + (p.isLeaf() ? p.symbol : "Internal") + " with frequency " + p.frequency +
                               ", " + (q.isLeaf() ? q.symbol : "Internal") + " with frequency " + q.frequency);

            // Create a new internal node with these two nodes as children
            Node r = new Node(p.frequency + q.frequency);
            r.left = p;
            r.right = q;

            // Insert the new node back into the priority queue
            pq.insert(r);
            System.out.println("Inserted into PQ: Internal node with frequency " + r.frequency);
            System.out.print("Current Priority Queue: ");
            pq.display();
        }

        // The remaining node is the root of the Huffman Tree
        Node root = pq.remove();
        System.out.println("\nFinal Huffman Tree Root: Frequency " + root.frequency + "\n");

        // Generate Huffman Codes by traversing the tree
        Map<Character, String> codes = new HashMap<>();
        generateHuffmanCodes(root, "", codes);

        return codes;
    }

    // Example Usage
    public static void main(String[] args) {
        try {
            // Sample symbols and their frequencies
            char[] symbols = {'a', 'b', 'c', 'd', 'e'};
            int[] frequencies = {45, 13, 12, 16, 9};

            System.out.println("Huffman Coding Process:\n");
            Map<Character, String> codes = huffmanCoding(symbols, frequencies);

            System.out.println("Huffman Codes for the given symbols:");
            for (int i = 0; i < symbols.length; i++) {
                System.out.println("Symbol: " + symbols[i] + ", Code: " + codes.get(symbols[i]));
            }

            // Another Example with unique symbols
            // char[] uniqueSymbols = {'W', 'h', 'e', 'n', 'v', 'r'};
            // int[] uniqueFrequencies = {23, 8, 5, 14, 22, 18};

            // System.out.println("Huffman Coding Process for unique symbols:\n");
            // Map<Character, String> codes2 = huffmanCoding(uniqueSymbols, uniqueFrequencies);

            // System.out.println("Huffman Codes for the given symbols:");
            // for (int i = 0; i < uniqueSymbols.length; i++) {
            //     System.out.println("Symbol: " + uniqueSymbols[i] + ", Code: " + codes2.get(uniqueSymbols[i]));
            // }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}