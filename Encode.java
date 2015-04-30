import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode {
  public static void main(String[] args) throws Exception {

    FileInputStream inFile = new FileInputStream(args[0]);
    FileOutputStream outFile = new FileOutputStream(args[1]);

    BitInputStream in = new BitInputStream(inFile);
    BitOutputStream out = new BitOutputStream(outFile);

    // Table containing frequency of all occourences
    // ie. frequencies[24] = 4 indicates that the 24 occures 4 times
    int[] frequencies = new int[256];    

    int bit;
    String bytelist = "";
    while ( (bit = in.readBit()) != -1 ) {
      bytelist += "" + bit;
      if (bytelist.length() % 8 == 0) {
        frequencies[Integer.parseInt(bytelist, 2)] += 1;
        bytelist = "";
      }      
    }

    in.close();

    DictBinTree.Node node = (DictBinTree.Node) Huffman.makeHuffmanTree(frequencies).data;
    printChildren(node);
  }

  private static void printChildren(DictBinTree.Node n) {
    if (n != null) {
      printChildren(n.left);
      System.out.println(n.key);
      printChildren(n.right);
    }
  }
}
