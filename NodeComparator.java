import java.util.Comparator;

/** Explains how to compare two Nodes
 * @author chiarazizza
 */
public class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			if (n1.frequency > n2.frequency) {
				return 1;
			} else if (n1.frequency < n2.frequency) {
				return -1;
			} else {
				return 0;
			}
		}
	}
