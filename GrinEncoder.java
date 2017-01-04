import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrinEncoder {
	
	/** Reads a text file and creates a map with the unique characters as keys
	 * and their corresponding frequencies as the values
	 * @param file a text file
	 * @return a Map of character ASCII values and their frequencies in the file
	 * @throws IOException
	 */
	private Map<Short, Integer> createFrequencyMap(String file) throws IOException {
		BitInputStream in = new BitInputStream(file);
		Map<Short, Integer> m = new HashMap<Short, Integer>();
		
		/* Walks through file one character at a time and sees if map already contains
		 * that character. If the character already exists, the frequency of that character
		 * is increased by one. If not, a new entry is made with a frequency of 1
		 */
		while(in.hasBits()) {
			short ch = (short)in.readBits(8); //the next character in the file
			if(m.containsKey(ch)) {
				m.put(ch, m.get(ch) + 1);
			} else {
				m.put(ch, 1);
			}
		}
		
		/* Closes the BitStream and returns the new Map */
		in.close();
		return m;
	}

	/** Encodes a text file
	 * @param infile a text file
	 * @param outfile the encoded file
	 * @throws IOException
	 */
	public void encode(String infile, String outfile) throws IOException {
		Map<Short, Integer> map = createFrequencyMap(infile);
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);

		/* Constructs a HuffmanTree */
		HuffmanTree tree = new HuffmanTree(map);
		
		/* Writes magicNumber and number of leaf nodes to file */
		out.writeBits(1846, 32);  //converts 1846 into binary	
		out.writeBits(tree.getLeafCount(), 32);
		
		Iterator<Short> keySet = map.keySet().iterator();
		
		/* Writes character ASCII values and their frequencies to the file */
		while (keySet.hasNext()) {
			short key = keySet.next();
			out.writeBits(key, 16);
			out.writeBits(map.get(key), 32);
		}
		
		/* Encodes the contents within the text file */
		tree.encode(in, out);
		
		/* Closes both BitStreams */
		in.close();
		out.close();
	}
}
