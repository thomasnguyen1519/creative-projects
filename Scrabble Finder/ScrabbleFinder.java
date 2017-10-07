


// need to create a heap for a PQ
// use LetterInventory to prune the dictionary
// compile the point totals of the words left in the pruned dictionary
// create the client side code (main)
// preprocess the dictionary of words for their point values


import java.util.*;
import java.io.*;

public class ScabbleFinder {

	public static final int[] LETTER_POINTS = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1,
											   1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

	public PriorityQueue topWordChoices;
	private ScrabbleBoard board;

	public ScrabbleFinder(String input) {
		topWordChoices = new PriorityQueue();
		findOptions(input);
		// board = new ScrabbleBoard();
	}

	public void buildOptions(String letters) {
		LetterInventory letters = new LetterInventory(letters);
	}

	public isEmpty() {
		return topWordChoices.isEmpty();
	}


}