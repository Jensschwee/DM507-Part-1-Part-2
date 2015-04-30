import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {
  public static void main(String[] args) throws Exception {

    FileInputStream inFile = new FileInputStream(args[0]);

    BitInputStream in = new BitInputStream(inFile);


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


   Element e = Huffman.makeHuffmanTree(frequencies);
      DictBinTree.Node n = (DictBinTree.Node)e.data;
      String[] encodeTable = Huffman.makeTranslateFromHuffmanTree(n);

      writeFile(args, frequencies, encodeTable);
  }

    private static void writeFile(String[] args, int[] frequencies, String[] encodeTable) throws IOException {
        FileInputStream inFile;
        BitInputStream in;
        String bytelist;
        int bit;
        FileOutputStream outFile = new FileOutputStream(args[1]);
        BitOutputStream out = new BitOutputStream(outFile);
        for(int i : frequencies)
            out.writeInt(i);

        inFile = new FileInputStream(args[0]);
        in = new BitInputStream(inFile);
        bytelist = "";
        while ( (bit = in.readBit()) != -1 ) {
            bytelist += "" + bit;
            if (bytelist.length() % 8 == 0) {
                for(char c : encodeTable[Integer.parseInt(bytelist, 2)].toCharArray())
                    out.writeBit(Integer.parseInt(Character.toString(c)));
                bytelist = "";
            }
        }
        in.close();
        out.close();
    }

    private static void printChildren(DictBinTree.Node n) {
    if (n != null) {
      printChildren(n.left);
      System.out.println(n.key);
      printChildren(n.right);
    }
  }
}
