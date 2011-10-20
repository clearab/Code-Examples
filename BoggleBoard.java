//aclear16
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/** The BoggleBoard class instantiates an instance of a BoggleBoard.
 * With an argument of 0, the board will be created from a file, otherwise,
 * pass the size of the required board.
 * @author aclear16
 *
 */
public class BoggleBoard {

	private String[][] board;
	private boolean[][] searched;
	//the alphabet array emphasizes vowels to create a more "playable" board
	private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "A", "E", "I", "O", "E", "A", "E", "I", "O", "E",
			"R", "S", "T", "L", "N"};
	private int size;
	private Random rand = new Random();

	/**The constructor for the BoggleBoard class, if passed a 0 will create a 
	 * board from file, otherwise will create a board of size s
	 * 
	 * @param s size of the board to be created
	 */
	public BoggleBoard(int s) {
		
		//creates a board from a file
		if (s==0) {
			Scanner inScan = null;
			try {
				File in = new File("board.txt"); 
				inScan = new Scanner (in); }
			catch (FileNotFoundException e) {
				System.out.println("FILE NOT FOUND.");
				System.exit(1);
			}
			try {
				size = Integer.parseInt(inScan.next());

			}
			catch (NumberFormatException e) {
				System.out.println("BAD DATA");
				System.exit(1);
			}

			board = new String[size][size];
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					board[row][col] = inScan.next();
				}
			}

			searched = new boolean[size][size];
			resetSearched();
		}
		
		//creates a random board of size s
		else {
			size = s;
			board = new String[size][size];
			searched = new boolean[size][size];
			resetSearched();
			for (int i = 0;i<size;i++) {
				for (int j = 0;j<size;j++) {
					board[i][j]=alphabet[rand.nextInt(40)];
				}
			}
		}

	}

	/**Method to print the boggle board to the console
	 * 
	 */
	public void printBoard() {
		System.out.print("+");
		for(int i = 0; i < 2*size + 1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");
		for(int j = 0; j < size;j++) {
			System.out.print("| ");
			for(int k = 0; k < size; k++) {
				System.out.print(board[j][k] + " ");
			}
			System.out.print("|\n");
		}
		System.out.print("+");
		for(int i = 0; i < 2*size+1; i++) {
			System.out.print("-");
		}
		System.out.print("+\n");
	}

	/**
	 * The wrapper class for searching the board for the given word.
	 * Will begin a new search for each possible starting point on the board
	 * @param word the word to be searched
	 * @return true if the word is found
	 */
	public boolean onBoard(String word) {
		boolean test = false;
		int indexR = 0;
		int indexC = 0;
		resetSearched();

		//check to see if the word is the empty string
		if (word.equalsIgnoreCase(""))
			return false;
		else {
			//loop through each possible starting point, until the word is found
			//or the end of the board is reached
			while (!test && indexR < size){
				while (!test && indexC < size){
					test = onBoard(word,0,indexR,indexC);
					indexC++;
					resetSearched();
				}
				indexC = 0;
				indexR++;
				resetSearched();
			}
			return test;
		}

	}

	/**This method recursively searches the boggle board to see if the given word
	 * is on the board.
	 * 
	 * @param word the word to be searched for
	 * @param index the current letter being looked for
	 * @param r the row where the search should begin
	 * @param c the column where the search should begin
	 * @return true if the word is found, false otherwise
	 */
	private boolean onBoard(String word, int index, int r, int c) {

		//check to see if [r,c] is on the board, and has not been searched
		if (c >= 0 && c < size && r >= 0 && r<size && searched[r][c]==false){
			searched[r][c] = true;
			//check to see if the letter at [r,c] is the next letter in the word
			if (board[r][c].equalsIgnoreCase(word.substring(index,index+1))) {
				//return true if it is the last letter in the word
				if (index == word.length()-1) {
					return true;
				}
				//search each surrounding letter
				else if (
						(onBoard(word,index+1,r,c+1)) ||
						(onBoard(word,index+1,r+1,c+1)) ||
						(onBoard(word,index+1,r-1,c+1)) ||
						(onBoard(word,index+1,r,c-1)) ||
						(onBoard(word,index+1,r+1,c-1)) ||
						(onBoard(word,index+1,r-1,c-1)) ||
						(onBoard(word,index+1,r-1,c)) ||
						(onBoard(word,index+1,r+1,c)) ) return true;
			}

			else {
				searched[r][c] = false; 
			}

		}
		return false;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**Resets the searched array to all false
	 * 
	 */
	public void resetSearched() {
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				searched[row][col] = false;
			}
		}
	}
}
