import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {	
	private Node root;
	private Map<Integer, String> encodeMap; //a Map with ASCII values of characters as keys and their
											//unique paths in the HuffmanTree as values
	private Map<String, Integer> decodeMap; //a Map with "01" paths as keys and their corresponding
											//character ASCII values as values.
	private int leafCount;

	/** Constructs a HuffmanTree from the given frequency map of 9-bit values
	 * @param m a Map.
	 */
	public HuffmanTree(Map<Short, Integer> m) {
		this.root = new Node();
		this.encodeMap = new HashMap<Integer, String>();
		this.leafCount = 0;

		/* Creates PriorityQueue from Map */
		PriorityQueue<Node> pq = buildQueue(m);

		/* Creates HuffmanTree */
		buildTree(pq);
		
		/* Populates encodeMap and decodeMap with appropriate paths for each character */
		buildPath(this.root, "");
	}

	/** Constructs a PriorityQueue from a Map
	 * @param m a Map
	 * @return pq a PriorityQueue
	 */
	private PriorityQueue<Node> buildQueue(Map<Short, Integer> m) {
		/* Instructs how to compare two Nodes */
		Comparator<Node> compareNode = new NodeComparator();
		PriorityQueue<Node> pq = new PriorityQueue<Node>(compareNode);
		
		/* Inserts EOF into Map */
		m.put((short) 256, 1);

		/* Populates PriorityQueue */
		if (m.isEmpty()) {
			throw new IllegalArgumentException("Map must not be empty.");
		} else {
			Iterator<Short> keySet = m.keySet().iterator();
			while (keySet.hasNext()) {
				short ch = (short) keySet.next();
				pq.add(new Node(ch, m.get(ch), null, null));
			}
		}
		return pq;
	}

	/** Constructs a HuffmanTree from a PriorityQueue
	 * @param pq a PriorityQueue
	 */
	private void buildTree(PriorityQueue<Node> pq) {
		if (pq.isEmpty()) {
			throw new IllegalArgumentException("PriorityQueue must not be empty.");
		} else {
			while (pq.size() > 1) {
				Node temp1 = pq.poll();
				Node temp2 = pq.poll();
				pq.add(new Node(temp1.frequency + temp2.frequency, temp1, temp2));
			}

			/* Assigns root to new tree */
			this.root = pq.poll();
		}
	}
	
	/** Recursively searches through a HuffmanTree and builds the "01" pathways to each leaf,
	 * storing the appropriate Node character value and its corresponding path in encodeMap
	 * while also counting the number of leaves in the tree
	 * @param cur the current Node
	 * @param s a String containing the pathway values (0 for left, 1 for right)
	 */
	private void buildPath(Node cur, String s) {
		if (cur.left != null && cur.right != null) {
			buildPath(cur.left, s + "0");
			buildPath(cur.right, s + "1");
		} else {
			this.encodeMap.put((int) cur.character, s);
			this.leafCount = this.leafCount + 1;
		}

		createDecodeMap();
	}
	
	/** Creates a Map to decode an encoded file
	 */
	private void createDecodeMap() {
		this.decodeMap = new HashMap<String, Integer>();
		Iterator<Integer> keySet = encodeMap.keySet().iterator();
		
		while(keySet.hasNext()) {
			int key = keySet.next();
			this.decodeMap.put(encodeMap.get(key), key);
		}
	}
	
	/** Returns the number of leaves in the HuffmanTree
	 * @return the number of leaves
	 */
	public int getLeafCount() {
		return this.leafCount;
	}
	
	/** Encodes the file given as a stream of bits into a compressed format using
	 * this Huffman tree. The encoded values are written, bit-by-bit to the
	 * given BitOuputStream
	 * @param in the BitInputStream being read
	 * @param out the BitOutputStream being written to
	 */
	public void encode(BitInputStream in, BitOutputStream out) {
		int index = 0;
		boolean proceed = true;
		
		while(proceed) {
			if (!in.hasBits()) {
				index = 256; //the EOF index
				proceed = false;
			} else {
				index = in.readBits(8); //the ASCII value of the next character
			}

			String path = encodeMap.get(index); //the "01" string designated for each character
			
			for (int i = 0; i < path.length(); i++) {
				if (path.charAt(i) == '0') {
					out.writeBit(0);
				} else if (path.charAt(i) == '1') {
					out.writeBit(1);
				} else {
					throw new IllegalArgumentException("Not '0' or '1'");
				}
			}
		}
	}

	
	/** Decodes a stream of huffman codes from a file given as a stream of bits
	 * into their uncompressed form, saving the results to the given output stream
	 * @param in the BitInputStream being read
	 * @param out the BitOutputStream being written to
	 */
	public void decode(BitInputStream in, BitOutputStream out) {
		/* path is used to store the binary string of each character in the file
		 * to be compared to the keys in decodeMap
		 */
		String path = "";

		while(in.hasBits()) {
			path += in.readBit(); //converts to "1" or "0"
			
			if(this.decodeMap.containsKey(path)) {		
				/* If value is EOF, don't output to file */
				if(this.decodeMap.get(path) == 256) {
					return;
				}

				/* Converts from encoded to original letters */
				out.writeBits(this.decodeMap.get(path), 8);
				
				/* Resets path string */
				path = "";
			}
		}
	}
}