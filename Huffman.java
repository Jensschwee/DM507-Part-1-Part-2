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

        for (int i = 0; i < countElements; i++) {
            DictBinTree.Node node = tree.new Node(i);
            Element left = pqHeap.extractMin();
            Element right = pqHeap.extractMin();
            node.left = tree.new Node(left.key);
            node.right = tree.new Node(right.key);
            node.key = node.left.key + node.right.key;
            pqHeap.insert(new Element(node.key, node));
        }

        return pqHeap.extractMin();
    }
}
