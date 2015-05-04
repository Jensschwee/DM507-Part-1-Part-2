import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {
  
  public static void main(String[] args) throws Exception {

    FileInputStream inFile = new FileInputStream(args[0]);
    FileOutputStream outFile = new FileOutputStream(args[1]);

    BitInputStream in = new BitInputStream(inFile);
    BitOutputStream out = new BitOutputStream(outFile);

    // 1. passthrough - build huffman tree and encoding
    int bit;
    int[] frequencies = new int[256];
    String bytelist = "";
    String[] encodedTable;

    while ((bit = in.readBit()) != -1) {
      bytelist += "" + bit;
      if (bytelist.length() % 8 == 0) {
        frequencies[Integer.parseInt(bytelist, 2)] += 1;
        bytelist = "";
      }      
    }

      for (int i : frequencies) {
          out.writeInt(i);
      }

    Element e = Huffman.build(frequencies);
      if(e != null) {
          encodedTable = Huffman.encode((DictBinTree.Node) e.data);

          in.close();

          // 2. passthrough - write code to file
          inFile = new FileInputStream(args[0]);
          in = new BitInputStream(inFile);



          while ((bit = in.readBit()) != -1) {
              bytelist += "" + bit;
              if (bytelist.length() % 8 == 0) {
                  for (char c : encodedTable[Integer.parseInt(bytelist, 2)].toCharArray()) {
                      out.writeBit(Integer.parseInt(Character.toString(c)));
                  }
                  bytelist = "";
              }
          }
          in.close();
          out.close();
      }

  }

}