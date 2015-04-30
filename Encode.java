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

    for (int i = 0; i < frequencies.length; i++) {      
      System.out.println(frequencies[i]);
    }

    huffman(frequencies);

    // TODO: output stuff
  }

  private static void huffman(int[] table) {
    PQ pqHeap = new PQHeap(table.length);
    DictBinTree tree = new DictBinTree();

    for(int i = 0; i < table.length; i++) {
      if(table[i] != 0) {
        pqHeap.insert(new Element(table[i], tree.new Node(i)));
      }
    }
  }
}
