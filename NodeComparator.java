import java.util.Comparator;

/** Explains how to compare two Nodes
 * @author chiarazizza
 */

//http://stackoverflow.com/questions/683041/java-how-do-i-use-a-priorityqueue
//https://www.tutorialspoint.com/java/java_using_comparator.htm
//http://algs4.cs.princeton.edu/55compression/Huffman.java.html
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
