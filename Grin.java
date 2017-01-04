import java.io.IOException;

public class Grin {

	/* cd Documents
	 * cd Workspace
	 * cd "Homework 8"
	 * cd bin
	 * java Grin (arguments)
	 * 
	 * cd ..
	 * diff TestInput.txt TestDecode.txt
	 */
	public static void main(String[] args) throws IOException {
		if(args[0].equals("encode")) {
			GrinEncoder encode = new GrinEncoder();
			encode.encode(args[1], args[2]);
		} else if(args[0].equals("decode")) {
			GrinDecoder decode = new GrinDecoder();
			decode.decode(args[1], args[2]);
		} else {
			throw new IllegalArgumentException("Not instructed to 'encode' or 'decode'");
		}
	}
}
