# Huffman-Coding

Description: This program encodes and decodes files using Huffman’s algorithm, which is a particular type of optimal prefix code that is commonly used for lossless data compression. It was written in Java using the Eclipse IDE, the Map interface, trees, and recursion.

This algorithm can either encode a file using letter frequencies to construct a Huffman tree or decode a file that was encoded with a Huffman tree. To encode a file, once the Huffman codes have been created, the program walks through a file, character-by-character, and replaces each character with its unique code (in binary). To decode a file, the Huffman tree is repeatedly walked from root to leaf, using the bits found in the encoded file as a guide. Whenever a leaf is hit, the code outputs its corresponding character, terminating the process once the end-of-file character is output.

Contributors: Chiara Zizza and Tapiwa Zvidzwa

http://www.cs.grinnell.edu/~osera/courses/csc207/16fa/homeworks/08-grin-compression.html
