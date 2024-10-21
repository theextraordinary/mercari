import java.util.Scanner;

// Define the Trie node structure
class TrieNode {
    TrieNode[] children = new TrieNode[2]; // One for bit 0 and one for bit 1
    TrieNode() {
        children[0] = null;
        children[1] = null;
    }
}

public class Main {
    // Insert a number into the Trie
    public static void insert(TrieNode root, int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; --i) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    // Find the maximum XOR of num with the numbers in the Trie that is <= k
    public static int findMaxXor(TrieNode root, int num, int k) {
        TrieNode node = root;
        int maxXor = 0, maxK = 0;

        for (int i = 31; i >= 0; --i) {
            int bitNum = (num >> i) & 1;
            int bitK = (k >> i) & 1;

            // Try to maximize XOR
            int targetBit = 1 - bitNum; // Try to choose the opposite bit to maximize XOR

            if (node.children[targetBit] != null && (maxK | (1 << i)) <= k) {
                // This branch can potentially give a valid XOR <= k
                maxXor |= (1 << i);
                maxK |= (1 << i);
                node = node.children[targetBit];
            } else if (node.children[bitNum] != null) {
                // Follow the current bit if choosing opposite would exceed k
                node = node.children[bitNum];
            } else {
                return maxXor; // No valid path, return current max XOR
            }
        }
        return maxXor;
    }

    // Main function to find the maximum XOR
    public static int maxXor(int lo, int hi, int k) {
        TrieNode root = new TrieNode();
        int max_xor = 0;

        // Insert all numbers from the range [lo, hi] into the Trie
        for (int a = lo; a <= hi; ++a) {
            insert(root, a);
        }

        // Query the Trie for each number to find the maximum XOR <= k
        for (int a = lo; a <= hi; ++a) {
            int currXor = findMaxXor(root, a, k);
            max_xor = Math.max(max_xor, currXor);
        }

        return max_xor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lo = sc.nextInt();
        int hi = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(maxXor(lo, hi, k));
        sc.close();
    }
}
