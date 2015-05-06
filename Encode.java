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
        String readBits = "";
        String[] encodedTable = null;

        // Read the input file as bits; seperate at 8 bits (1 byte).
        // Add one to the frequency of the byte on occurrence.
        while ((bit = in.readBit()) != -1) {
            readBits += "" + bit;
            if (readBits.length() % 8 == 0) {
                frequencies[Integer.parseInt(readBits, 2)] += 1;
                readBits = "";
            }
        }

        // Write all frequencies to the output
        for (int i : frequencies) out.writeInt(i);

        try {
            Element e = Huffman.build(frequencies);
            encodedTable = Huffman.encode((DictBinTree.Node) e.data);
        } catch (NullPointerException ex) {
            System.err.println("Warning: Input is empty.");
        }

        in.close();

        // 2. passthrough - write code to file
        inFile = new FileInputStream(args[0]);
        in = new BitInputStream(inFile);

        // Read the input again by byte. Write chars from the encoded table
        // at the position of the byte to the output
        while ((bit = in.readBit()) != -1) {
            readBits += "" + bit;
            if (readBits.length() % 8 == 0) {
                for (char c : encodedTable[Integer.parseInt(readBits, 2)].toCharArray()) {
                    out.writeBit(Integer.parseInt(Character.toString(c)));
                }
                readBits = "";
            }
        }

        in.close();
        out.close();

    }

}
