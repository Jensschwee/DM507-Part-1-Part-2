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

    while ((bit = in.readBit()) != -1) {
      bytelist += "" + bit;
      if (bytelist.length() % 8 == 0) {
        frequencies[Integer.parseInt(bytelist, 2)] += 1;
        bytelist = "";
      }      
    }

    /*
     * Prints the array of frequencies
     *
    for (int i = 0; i < frequencies.length; i++) {
      System.out.printf("The frequency of %d is %d\n", i, frequencies[i]);
    }*/

    Element e = Huffman.build(frequencies);
    DictBinTree.Node node = (DictBinTree.Node) e.data;

    //printChildren(node);

    String[] encodedTable = Huffman.encode(node);

    writeFile(args, frequencies, encodedTable);

    in.close();
  }

  private static void writeFile(String[] args, int[] frequencies, String[] encodeTable) throws IOException {
    FileInputStream inFile = new FileInputStream(args[0]);
    FileOutputStream outFile = new FileOutputStream(args[1]);

    BitInputStream in = new BitInputStream(inFile);;        
    BitOutputStream out = new BitOutputStream(outFile);

    String bytelist = "";
    int bit;

    for(int i : frequencies) {
      out.writeInt(i);
    }

    while ((bit = in.readBit()) != -1) {
      bytelist += "" + bit;
      if (bytelist.length() % 8 == 0) {
        for(char c : encodeTable[Integer.parseInt(bytelist, 2)].toCharArray()) {
          out.writeBit(Integer.parseInt(Character.toString(c)));
        }
        bytelist = "";
      }
    }
    in.close();
    out.close();
  }

  private static void printChildren(DictBinTree.Node n) {
    if (n != null) {
      System.out.println(n.key);
      printChildren(n.left);
      printChildren(n.right);
    }
  }
}