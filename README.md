# Grin-Compression

Description: This program encodes and decodes files using Huffman’s algorithm, which is a particular type of optimal prefix code that is commonly used for lossless data compression. It was written in Java using the Eclipse IDE, the Map interface, trees, and recursion.

The Huffman Tree can either encode a file whose letter frequencies were used to generate the tree or decode a file that was encoded with a Huffman Tree. To encode a file, once the Huffman codes have been created, the program walks through a file, character-by-character, and replaces each character with its unique code (in binary). To decode a file, the Huffman Tree is repeatedly walked from root to leaf, using the bits found in the encoded file as a guide. Whenever a leaf is hit, the code outputs its corresponding character, terminating the process once the end-of-file character is outputed.

Contributors: Chiara Zizza and Tapiwa Zvidzwa

http://www.cs.grinnell.edu/~osera/courses/csc207/16fa/homeworks/08-grin-compression.html
