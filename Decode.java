import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Decode {
    public static void main(String[] args) throws Exception {

        // Open input and output byte streams to/from files.
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);

        // Wrap the new bitstreams around the input/output streams.
        BitInputStream in = new BitInputStream(inFile);
        BitOutputStream out = new BitOutputStream(outFile);

        // Reads all the frequencies from input file, and stores it in an int array
        int[] frequencies = new int[256];
        try{
            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = in.readInt();
            }
        } catch (IOException e)
        {
            System.err.println("This file has not the right number of ints in the start of the file");
        }


        // Builds the huffman tree from the frequencies.
        // If a huffman tree was built then e is the root of this tree
        Element e = Huffman.build(frequencies);

        try {
            // Find the code for all input in the huffman tree
            Map<String, Integer> decodeTable = Huffman.decode(e);

            String readBits = "";
            int bit;

            // Have many character is there in the tree, 
            // and therefor have manny shall there be writen in the output file
            int writeCount = e.key;

            // Reads the rest of the file, bit for bit.
            while ((bit = in.readBit()) != -1) {

                readBits += "" + bit;
                // Looks in the HashMap for whether or not these bits mean something in the huffman tree
                // if it does, the decode contains the ints that are to be writen to the output file
                Integer decode = decodeTable.get(readBits);
                if (decode != null) {
                    // Finds the bitstring for this int.
                    String test = Integer.toBinaryString(decode);

                    // If this bit string do not fill out a byte then add the rest of the byte in form of "0"'s
                    for (int i = test.length() - 8; i < 0; i++) {
                        out.writeBit(0);
                    }
                    // Writes the int in the form of bits to the output file.
                    for (char c : test.toCharArray()) {
                        out.writeBit(Integer.parseInt(Character.toString(c)));
                    }
                    // Clears the readBits so the next character can be decoded
                    readBits = "";
                    writeCount--;

                    // Are there more character to be written?
                    if (writeCount < 1) break;
                }
            }
        } catch (NullPointerException ex) {
            System.err.println("Warning: Empty encoding.");
        }

        in.close();
        out.close();
    }
}
