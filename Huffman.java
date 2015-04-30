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
            node.left = (DictBinTree.Node)left.data;
            node.right = (DictBinTree.Node)right.data;
            int counter = left.key + right.key;
            pqHeap.insert(new Element(counter, node));
        }

        return pqHeap.extractMin();
    }

    public static String[] makeTranslateFromHuffmanTree(DictBinTree.Node root)
    {
        String[] table = new String[256];

        wakeHuffmanTree(root,table,"");

        return table;
    }

    private static void wakeHuffmanTree(DictBinTree.Node n, String[] table, String s)
    {
        if(n != null)
        {
            if(n.left == null && n.right == null)
            {
                table[n.key] = s;
            }
            else
            {
                wakeHuffmanTree(n.left, table, s+"0");
                wakeHuffmanTree(n.right, table, s+"1");
            }
        }
    }
}
