/*
Name: Andrew Clear
  Assignment: Lab Number 09
  Title: BoggleBoard
  Course: CSCE 270
  Lab Section: 2
  Semester: Spring 2011
  Instructor: David Wolff
  Date: May, 1
  Sources consulted: textbook, java docs, Prof Wolff
  Program description: Creates a BoggleBoard, either from a file or a random board.
  Allows the user to enter words and tests if they are in the dictionary and on
  the board. If the word is good, the user's score is calculated based on Boggle
  rules, and keeps a running total of their score
  Known Bugs: the value entered when prompted for a random or file board is not
  				verified. There should also be two constructors, rather than one
  				so that the '0' being passed is not required.
  Creativity: Added the ability to create either a random board, or a board from a file.
  				Tweaked the alphabet to generate a more playable board, emphasizing
  				vowels and r, s, t, l, and n
*/
import java.util.Scanner;


/**Runs the BoggleBoard, instantiating a BoggleBoard and a Dictionary object
 * and keeping track of the users score.
 * @author aclear16
 */
public class BoggleMain {

	/**The main method, beginning the program.
	 * @param args
	 */
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		Scanner key = new Scanner(System.in);
		String word = "";
		//sentinel variables
		boolean testContinue = true;
		boolean testOnBoard = false;
		boolean testInDict = false;
		boolean typeTest = false;
		
		int score = 0;
		int totalScore = 0;
		int size = 0;
		String boardType = "";

		//Prompt to see if the user would like a random board, or a file
		System.out.println("Would you like a random board(R), or a board from " +
		"a file(F)?");
		boardType=key.nextLine();

		if (boardType.equalsIgnoreCase("r")) {
			while (size < 3 || size > 8) {
				System.out.println("What size board would you like? (Enter an " +
				"integer between 3 and 7)");
				size = key.nextInt();
			}
		}

		//create the board
		BoggleBoard b = new BoggleBoard(size);
		key.nextLine();


		//prompt the user for word
		while (testContinue) {
			b.printBoard();
			System.out.println("Enter a word (enter \"asdf\" to quit): ");
			word = key.nextLine();
			if (word.equalsIgnoreCase("asdf")) testContinue = false;
			else {
				testOnBoard = b.onBoard(word);
				testInDict = d.wordSearch(word.toLowerCase());

				//calculate the score if the word was good
				if (testOnBoard && testInDict && word.length() > 2) {
					if(word.length()==3 || word.length()==4)
						score = 1;
					else if (word.length()==5)
						score = 2;
					else if (word.length()==6)
						score = 3;
					else if (word.length()==7)
						score = 5;
					else
						score = 11;
					totalScore += score;
					System.out.println("Good word, score is: " + score
							+ ". Total score is: " + totalScore);
				}
				//if the word is not good, print the appropriate message
				else if (testOnBoard && testInDict && word.length() < 3)
					System.out.println("You word is too short.");

				else if (testOnBoard && !testInDict)
					System.out.println("Your word is not in the dictionary (but is " +
					"on the board)");

				else if (!testOnBoard && testInDict)
					System.out.println("Your word is not on the board (but is " +
					"in the dictionary)");

				else
					System.out.println("Bad word. BAAAAAAAD.");

				testOnBoard = false;
				testInDict = false;
			}
		}
	}
}