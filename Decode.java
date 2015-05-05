import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

public class Decode {
  public static void main(String[] args) throws Exception {

    // Open input and output byte streams to/from files.
    FileInputStream inFile = new FileInputStream(args[0]);
    FileOutputStream outFile = new FileOutputStream(args[1]);

    // Wrap the new bit streams around the input/output streams.
    BitInputStream in = new BitInputStream(inFile);
    BitOutputStream out = new BitOutputStream(outFile);

    int[] frequencies = new int[256];
    Map<String, Integer> decodeTable;


    for (int i = 0; i < frequencies.length; i++) {
      frequencies[i] = in.readInt();
    }

    Element e = Huffman.build(frequencies);
    if (e != null) {
      decodeTable = Huffman.decode((DictBinTree.Node) e.data);

      String readBits = "";
      int bit;
      int wirterCount = e.key;

      while ((bit = in.readBit()) != -1) {
        readBits += "" + bit;
        Integer decode = decodeTable.get(readBits);
        if (decode != null) {
          String test = Integer.toBinaryString(decode);
          for (int i = test.length() -8; i < 0; i++) {
            out.writeBit(0);
          }
          for(char c : test.toCharArray())
          {
            out.writeBit(Integer.parseInt(Character.toString(c)));
          }
          //out.writeInt((int) decode);
          readBits = "";
          wirterCount--;
          if (wirterCount < 1)
            break;
        }
      }

      in.close();
      out.close();
    }
  }
}
