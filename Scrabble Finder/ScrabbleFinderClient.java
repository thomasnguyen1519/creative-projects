

import java.util.*;




public class ScrabbleFinderClient {


	public static void main(String[] args) {
		String letters = askWord();
		letters = letters.replaceAll("\\s+", "");
		chooseWord(letters);

	}

	public static String askWord() {
		Scanner input = new Scanner(System.in);
		System.out.print("What letters do you have in your hand? ");
		String response = input.nextLine();
		return response;
	}

	public static void chooseWord(String letters) {
		ScrabbleFinder bestWords = new ScrabbleFinder(letters);
		while (!bestWords.isEmpty()) {
			// go thru all the options with the client
		}
	}

}