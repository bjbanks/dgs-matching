// Bryson Banks

// DGS Algorithm:
// 	Initialization:
// 		1. For each good j, set p_j = 0 and owner_j = null.
// 		2. Initialize a queue Q to contain all bidders i.
// 		3. Fix delta = 1/(n_g+1), where n_g is the number of goods.
// 	While Q is not empty do:
// 		1. i = Q.deque().
// 		2. Find j that maximizes w_{ij} - p_j.
// 		3. If w_{ij} - p_j >= 0 then
// 			3a. Enque current owner_j into Q.
// 			3b. owner_j = i.
// 			3c. p_j = p_j + delta.
// 	Output: the set of (owner_j, j) for all j.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGS {
	
	static int size;	// size of the square matrix = num of cols = num of rows
	static int[][] w;	// weight of edges (x,y)

	// Takes in an input file containing a square matrix and size representing
	// a weighted bipartite graph. Using the DGS algorithm, finds the
	// maximum weighted matching for the given input graph, then outputs the
	// weight of the matching followed by a sorted list of edges in the matching
	public static void main(String[] args) {
		// verify one argument: inputFile
		if (args.length != 1) {
			System.out.println("Error. Java program DGS expects 1 argument specifying an input file.");
			return;
		}
		String inputFile = args[0];
				
		// parse the input file - will initialize size and w
		if (!parseInput(inputFile)) {
			return;
		}
		
		//TODO - find maximum weighted matching
		
		//TODO - print weight of matching followed by a sorted list of edges in the matching
	}

	// parse given input file
	// returns true if success, or false if parsing fails
	public static boolean parseInput(String inputFile) {
		// create file scanner object
		Scanner scanner;
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found: " + inputFile);
			return false;
		}
					
		// parse the matrix size from first line
		size = parseSize(scanner);
		if (size < 0) {
			System.out.println("Error. Failed to parse matrix size. Input file is incorrectly formatted.");
			scanner.close();
			return false;
		}

		// parse the square matrix values
		w = parseMatrix(scanner, size);
		if (w == null) {
			System.out.println("Error. Failed to parse weight matrix. Input file is incorrectly formatted.");
			scanner.close();
			return false;
		}
			
		// done parsing file, success
		scanner.close();
		return true;
	}

	// parse input file to retrieve matrix size
	// returns size, or -1 if parsing fails
	public static int parseSize(Scanner scanner) {
		if (scanner.hasNextInt()) {
			return scanner.nextInt();
		} else {
			return -1;
		}
	}		

	// parse input file to retrieve square matrix
	// returns matrix, or null if parsing fails
	public static int[][] parseMatrix(Scanner scanner, int size) {
		int[][] matrix = new int[size][size];
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			for (int colIndex = 0; colIndex < size; colIndex++) {
				if (scanner.hasNextInt()) {
					matrix[rowIndex][colIndex] = scanner.nextInt();
				} else {
					return null;
				}
			}
		}
		return matrix;
	}
}
