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

  public static String[] makeTranslateFromHuffmanTree(DictBinTree.Node root) {
    String[] table = new String[256];
    wakeHuffmanTree(root,table,"");
    return table;
  }

  private static void wakeHuffmanTree(DictBinTree.Node n, String[] table, String s) {
    if (n != null) {
      if (n.left == null && n.right == null) {
        table[n.key] = s;
      } else {
        wakeHuffmanTree(n.left, table, s+"0");
        wakeHuffmanTree(n.right, table, s+"1");
      }
    }
  }
}
