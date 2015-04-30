import java.util.stream.*;

public class Huffman {

    public static Element makeHuffmanTree(int[] table) {
        
        // Removes table indexes with frequency = 0 and counts them.
        int countElements = (int) IntStream.of(table)
            .filter(n -> n != 0)
            .count();

        DictBinTree tree = new DictBinTree();

        PQ pqHeap = new PQHeap(countElements);

        // Removes table indexes with frequency = 0 and
        // inserts elements into the pqHeap with the remaining indices
        IntStream.range(0, table.length)
          .filter(n -> table[n] != 0)   //
          .forEach(i -> pqHeap.insert(new Element(table[i], tree.new Node(i))));

        for (int i = 0; i < countElements-1; i++) {
            DictBinTree.Node node = tree.new Node(i);
            Element left = pqHeap.extractMin();
            Element right = pqHeap.extractMin();
            node.left = (DictBinTree.Node) left.data;
            node.right = (DictBinTree.Node) right.data;
            node.key = node.left.key + node.right.key;
            pqHeap.insert(new Element(node.key, node));
        }

        return pqHeap.extractMin();
    }    
}
