// Bryson Banks

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class DGS {
	
	static int size;		// size of the square matrix = num of cols = num of rows
	static int[][] w;		// weight of edges (x,y)
	
	static float[] p_y;		// value of y items
	static int[] owner_y;	// x owner of y items
	
	static LinkedList<Integer> Q;	// queue of bidders x
	
	static float delta;		// item value incrementing value
	
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
		
		// initialize variables for DGS algorithm
		initDGS();
		
		// augment the matching using DGS algorithm
		augmentMatching();
		
		// finally print results
		printMatching();
	}
	
	// initializes variables for DGS algorithm
	public static void initDGS() {
		p_y = new float[size];
		owner_y = new int[size];
		Q = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			// set starting price of all items to 0
			p_y[i] = 0.0f;
			// no items are yet owned
			owner_y[i] = -1;
			// add all bidders to auction queue
			Q.add(i);
		}
		// fix delta = 1/(nsize+1), where size is the number of items
		delta = (1.0f / (size + 1.0f));
	}
	
	// uses the DGS algorithm to find the maximum weighted matching
	public static void augmentMatching() {
		while (!Q.isEmpty()) {
			// take next bidder
			int i = Q.removeFirst();
			// determine item that maximizes profit for bidder
			int j = 0;
			float maxVal = Float.MIN_VALUE;
			for (int y = 0; y < size; y++) {
				float val = w[i][y] - p_y[y];
				if (val > maxVal) {
					j = y;
					maxVal = val;
				}
			}
			if (maxVal >= 0) {
				// if item has previous owner, add them to auction queue
				if (owner_y[j] != -1) {
					Q.add(owner_y[j]);
				}
				// assign bidder i as owner of item j
				owner_y[j] = i;
				// increase price of item
				p_y[j] = p_y[j] + delta;
			}
		}
	}
	
	// returns the weight of the current matching
	public static int getMatchingWeight() {
		int weight = 0;
		for (int y = 0; y < size; y++) {
			if (owner_y[y] != -1) {
				weight += w[owner_y[y]][y];
			}
		}
		return weight;
	}
	
	// prints weight of matching followed by a sorted list
	// of edges in the matching
	public static void printMatching() {
		System.out.println(getMatchingWeight());
		int[] edges = new int[size];
		for (int x = 0; x < size; x++) {
			edges[x] = -1;
		}
		for (int y = 0; y < size; y++) {
			if (owner_y[y] != -1) {
				edges[owner_y[y]] = y;
			}
		}
		for (int x = 0; x < size; x++) {
			if (edges[x] != -1) {
				System.out.println("(" + (x+1) + "," + (edges[x]+1) + ")");
			}
		}
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
