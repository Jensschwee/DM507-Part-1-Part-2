public class Huffman {

    public static Element makeHuffmanTree(int[] table) {
        int countElements = 0;
        DictBinTree tree = new DictBinTree();
        for(int i = 0; i < table.length; i++) {
            if(table[i] != 0) {
                countElements++;
            }
        }
        PQ pqHeap = new PQHeap(countElements);

        for (int i = 0; i < table.length; i++) {
            if(table[i] != 0) {
                pqHeap.insert(new Element(table[i], tree.new Node(i)));
            }
        }

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
