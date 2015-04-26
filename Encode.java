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
    int[] frequencies = new int[255];

    int bit;
    while ( (bit = in.readBit()) != -1 )
        frequencies[bit] += 1;

    in.close();

    huffman(frequencies);

    // TODO: output stuff
  }

  private static void huffman(int[] table) {

  }
}
