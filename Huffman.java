import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;

public class Huffman {

    // Because Node is an inner class of DictBinTree,
    // we must initialise a new tree
    private static DictBinTree tree = new DictBinTree();

    /**
     * Builds a new Huffman tree given a table of frequencies
     *
     * @param table - an array of frequencies
     * @return - the root node of the Huffman tree
     */
    public static Element build(int[] table) {
        int elementCount = (int) IntStream.of(table)
                .filter(n -> n != 0)
                .count();

        if (elementCount > 0) {
            PQ pqHeap = new PQHeap(elementCount);

            // Fill the heap with elementCount number of elements with
            // the frequency as the element key and a childless node with the value
            // of the character as the node key as the data.
            IntStream.range(0, table.length)
                    .filter(n -> table[n] != 0)
                    .forEach(n -> pqHeap.insert(new Element(table[n], tree.new Node(n))));

            for (int i = 0; i < elementCount - 1; i++) {
                Element left = pqHeap.extractMin();
                Element right = pqHeap.extractMin();
                int key = left.key + right.key;

                // Build a new node with the two smallest
                // The key of the Node  is the variable in the constructor
                DictBinTree.Node node = tree.new Node(key);

                // nodes as the children
                node.left = (DictBinTree.Node) left.data;
                node.right = (DictBinTree.Node) right.data;

                // Insert the new node back into the heap
                pqHeap.insert(new Element(key, node));
            }

            return pqHeap.extractMin();
        }
        return null;
    }

    /**
     * Uses Huffman coding to encode a Huffman tree
     *
     * @param root - the rootnode of the Huffman tree
     * @return - a table of encoded values
     */
    public static String[] encode(DictBinTree.Node root) {
        String[] table = new String[256];
        if(root.right != null || root.left != null)
            recursiveEncode(root, table, "");
        else
            recursiveEncode(root, table, "0");
        return table;
    }

    /**
     * Uses Huffman coding to decode a Huffman tree
     *
     * @param root - the rootnode of the Huffman tree
     * @return - a HashMap of decode values
     */
    public static Map<String, Integer> decode(DictBinTree.Node root) {
        Map<String, Integer> table = new HashMap<String, Integer>();
        if(root.right != null || root.left != null)
            recursiveDecode(root, table, "");
        else
            recursiveDecode(root, table, "0");
        return table;
    }

    /**
     * Recursively generates bitstrings based on a Huffman tree.
     *
     * @param node - current node that is being proccessed
     * @param table - the Map to insert decodes into
     * @param bitString - the string to insert into the table
     */
    private static void recursiveDecode(DictBinTree.Node node, Map<String, Integer> table, String bitString) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            table.put(bitString, node.key);
        } else {
            recursiveDecode(node.left, table, bitString + "0");
            recursiveDecode(node.right, table, bitString + "1");
        }
    }

    /**
     * Recursively generates bitstrings based on a Huffman tree.
     *
     * @param node - current node that is being proccessed
     * @param table - the table to insert strings into
     * @param bitString - the string to insert into the table
     */
    private static void recursiveEncode(DictBinTree.Node node, String[] table, String bitString) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            table[node.key] = bitString;
        } else {
            recursiveEncode(node.left, table, bitString + "0");
            recursiveEncode(node.right, table, bitString + "1");
        }
    }
}