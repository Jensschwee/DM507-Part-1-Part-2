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

        //Reads all the frequencies from input file, and store it in a int array
        int[] frequencies = new int[256];
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = in.readInt();
        }

        //builds the huffman tree from the frequencies.
        Element e = Huffman.build(frequencies);
        //if there was build a huffman tree from the frequencies table.
        //e is the root of the tree
        if (e != null) {
            //Find the code for all input in the huffman tree
            Map<String, Integer> decodeTable = Huffman.decode((DictBinTree.Node) e.data);


            String readBits = "";
            int bit;
            //Have manny character is there in the tree, and therefor have manny shall there be writen in the output file
            int writeCount = e.key;

            //reads the rest of the file, bit for bit.
            while ((bit = in.readBit()) != -1) {
                //readBits contains the data there has been read but not yet writen to the output file
                readBits += "" + bit;
                //looks in the HashMap for where or not these bit's means something in this huffman tree
                //if it do the decode contains the int there is to be writen in the output file
                Integer decode = decodeTable.get(readBits);
                if (decode != null) {
                    //finds the bit string for this int.
                    String test = Integer.toBinaryString(decode);

                    //if this bit string do not fill out a byte then add the rest of the byte in form of "0"'s
                    for (int i = test.length() - 8; i < 0; i++) {
                        out.writeBit(0);
                    }
                    //write the int in form of bits to the output file.
                    for (char c : test.toCharArray()) {
                        out.writeBit(Integer.parseInt(Character.toString(c)));
                    }
                    //clears the readBits so the next character can be decoded
                    readBits = "";
                    writeCount--;

                    //is there more character to be writen?
                    if (writeCount < 1) break;
                }
            }

            in.close();
            out.close();
        }
    }
}
