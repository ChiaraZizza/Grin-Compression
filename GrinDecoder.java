import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrinDecoder {

	/** Decodes an encoded file
	 * @param infile an encoded file
	 * @param outfile the file to output decoded content
	 * @throws IOException
	 */
	public void decode(String infile, String outfile) throws IOException {
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);	
		
		/* Reads in magicNumber */
		int magicNumber = in.readBits(32);
		
		/* Checks that magicNumber is correct and continues decoding process */
		if(magicNumber != 1846) {
			throw new IllegalArgumentException("Not appropriate magic number");
		} else {
			Map<Short, Integer> fmap = new HashMap<Short, Integer>();
			
			/* Reads the number of leaf nodes of HuffmanTree from encoded file */
			int leafCount = in.readBits(32);

			/* Builds decoder Map from encoded file */
			for(int i = 0; i < leafCount; i++) {
				fmap.put((short)in.readBits(16), in.readBits(32));
			}

			/* Builds HuffmanTree and uses it to decode the encoded file */
			HuffmanTree tree = new HuffmanTree(fmap);
			tree.decode(in, out);
		}
		
		/* Closes both BitStreams */
		in.close();
		out.close();
	}
}
