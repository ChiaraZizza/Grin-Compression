
public class Node {
		public Integer frequency;
		public short character;
		public Node left;
		public Node right;

		/** Constructor for leaf Nodes 
		 * @param c a short representing the character
		 * @param f an Integer representing the frequency of the character
		 * @param l the left Node
		 * @param r the right Node
		 */
		public Node(short c, Integer f, Node l, Node r) {
			this.character = c;
			this.frequency = f;
			this.left = l;
			this.right = r;
		}

		/** Constructor for interior Nodes
		 * @param f f an Integer representing the sum of the subtree frequencies
		 * @param l the left Node
		 * @param r the right Node
		 */
		public Node(Integer f, Node l, Node r) {
			this.frequency = f;
			this.left = l;
			this.right = r;
		}

		/** Constructor for initializing root
		 */
		public Node() {}
	}