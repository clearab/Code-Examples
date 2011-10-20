//aclear16
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**The dictionary class creates a Dictionary object, which reads a list of words
 * from a file called "words.txt". 
 * @author aclear16
 *
 */
public class Dictionary {

	//the array of words in the dictionary
	private ArrayList<String> dictionary = new ArrayList<String>();
	
	/**Default constructor for the Dictionary object, which attempts to read
	 * a file named "words.txt"
	 * 
	 */
	public Dictionary() {
		Scanner inScan = null;
		File in = new File("words.txt");
		try {
		inScan = new Scanner(in);
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND.");
			System.exit(1);
		}
		while (inScan.hasNext()) {
			dictionary.add(inScan.next());
		}
		
		inScan.close();
	}
	
	/**Wrapper class for the wordSearch method
	 * @param word the word to be searched
	 * @return
	 */
	public boolean wordSearch(String word) {
		return wordSearch(word, 0, dictionary.size()-1);
	}
	
	/**A recursive binary search to determine if the given word
	 * is in the "words.txt" file.
	 * @param word the word to be searched
	 * @param first holds the index
	 * @param last holds the other index
	 * @return true if the word is found
	 */
	private boolean wordSearch(String word, int first, int last) {
		if (first > last)
			return false;
		else {
			int middle = ( first + last )/ 2;
			String test = dictionary.get(middle);
			int compResult = word.compareTo(test.toLowerCase());
			if (compResult == 0)
				return true;
			else if (compResult < 0)
				return wordSearch(word.toLowerCase(), first, middle-1 );
			else
				return wordSearch(word.toLowerCase(), middle + 1, last);
		}
	}
	
	/**Returns a word at a particular index (was used in testing)
	 * @param i the index of the word
	 * @return the word at index[i]
	 */
	public String getWord(int i) {
		return dictionary.get(i);
	}
	
}
