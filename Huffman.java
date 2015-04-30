import java.util.stream.*;

public class Huffman {

  private static DictBinTree tree = new DictBinTree();

  /**
   * Builds a new Huffman tree given a table of frequencies
   *
   * param table - an array of frequencies
   * return - the root node of the Huffman tree
   */
  public static Element build(int[] table) {
    int elementCount, i;

    elementCount = (int) IntStream.of(table)
      .filter(n -> n != 0)
      .count();

    PQ pqHeap = new PQHeap(elementCount);

    // Fill the heap with elementCount number of elements with
    // the frequency as the element key and a childless node with the value
    // of the character as the node key as the data.
    IntStream.range(0, table.length)
      .filter(n -> table[n] != 0)
      .forEach(n -> pqHeap.insert(new Element(table[n], tree.new Node(n))));

    for (i = 0; i < elementCount-1; i++) {
      Element left, right;
      int key;

      DictBinTree.Node node = tree.new Node(i);

      left = pqHeap.extractMin();
      right = pqHeap.extractMin();

      node.left = (DictBinTree.Node) left.data;
      node.right = (DictBinTree.Node) right.data;

      key = left.key + right.key;

      node.key = key;

      pqHeap.insert(new Element(key, node));
    }
    return pqHeap.extractMin();
  }

  /**
   * Uses Huffman coding to encode a Huffman tree
   *
   * param root - the rootnode of the Huffman tree
   * return - a table of encoded values
   */
  public static String[] encode(DictBinTree.Node root) {
    String[] table = new String[256];
    recursiveEncode(root, table, "");
    return table;
  }

  public static void decode() {}

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
