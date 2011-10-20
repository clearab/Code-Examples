/*
Name: Andrew Clear
  Assignment: Lab 03
  Title: MediaLibrary
  Course: CSCE 270
  Lab Section: 02
  Semester: Spring 2011
  Instructor: David Wolff
  Date: the current date
  Sources consulted: javadocs, Nate, Prof. Wolff, internet
  Program description: Reads a file of MediaItem objects, stores it as an array
  and displays it through the GUI
  Known Bugs: none
  Creativity: all buttons implemented, output formatted nicely
  */

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**This class creates and manipulates an array of MediaItem objects
 * so that the GUI can display the results
 * @author aclear16
 *
 */
public class MediaLibrary implements MediaLibraryModel{
	private MediaItem mi[];
	private int numLines = 0;
	StringBuilder list = new StringBuilder();
	
	/* 
	 * (non-Javadoc)
	 * @see MediaLibraryModel#getCDsByMusicRating(int)
	 */
	@Override
	public String getCDsByMusicRating(int rating) {
		list.delete(0,list.length());
		list.append("CD's by Music Rating \n" +
				"================================ \n");
		int j = 1;
		for(int i = 0; i < numLines; i++ ) {
			if (mi[i] instanceof CD) {
				if (((CD)mi[i]).getMusic() >= rating){
					if (j < 10)
						list.append((j + ")  " + mi[i].toString() + "\n"));
					else
						list.append((j + ") " + mi[i].toString() + "\n"));
					j++;
				}
			}
		}
			
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#getDVDsByDirectingRating(int)
	 */
	@Override
	public String getDVDsByDirectingRating(int rating) {
		list.delete(0,list.length());
		list.append("DVD's by Directing Rating \n" +
				"================================ \n");
		int j = 1;
		for(int i = 0; i < numLines; i++ ) {
			if (mi[i] instanceof DVD) {
				if (((DVD)mi[i]).getDirecting() >= rating) {
					if (j < 10) //simply for formatting
						list.append((j + ")  " + mi[i].toString() + "\n"));
					else
						list.append((j + ") " + mi[i].toString() + "\n"));
					j++;
				}
			}
		}
			
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#getFullList()
	 */
	@Override
	public String getFullList() {
		list.delete(0,list.length());
		list.append("The Complete List \n" +
				"================================ \n");
		int j = 1;
		for(int i = 0; i < numLines; i++) {
			if (j < 10)
				list.append((j + ")  " + mi[i].toString() + "\n"));
			else
				list.append((j + ") " + mi[i].toString() + "\n"));
			j++;
		}
		return list.toString();
				
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#getItemsWithinBudget(double, double)
	 */
	@Override
	public String getItemsWithinBudget(double low, double high) {
		String title = String.format("Limited to the range: $%.2f to $%.2f \n" +
				"=============================== \n",low,high);
		list.delete(0,list.length());
		list.append(title);
		int j = 1;
		for (int i = 0; i < numLines; i++) {
			if (mi[i].getPrice() >= low && mi[i].getPrice() <= high) {
				if (j < 10)
					list.append((j + ")  " + mi[i].toString() + "\n"));
				else
					list.append((j + ") " + mi[i].toString() + "\n"));
				j++;
			}
		}
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#getTopTenList()
	 */
	@Override
	public String getTopTenList() {
		list.delete(0,list.length());
		list.append("The Top Ten \n" +
				"================================ \n");
		sortDesc();
		if (numLines < 10) {
			for(int i = 0; i < numLines; i++) {
				list.append((i+1) + ") " + mi[i].toString() + "\n");
			}
		}
		else {
			for(int i = 0; i < 10; i++) {
				list.append((i+1) + ") " + mi[i].toString() + "\n");
			}
		}
		
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#loadLibrary(java.lang.String)
	 * 
	 * Does not test the items in the file for validity
	 */
	@Override
	public void loadLibrary(String fileName) {
		
		File fn = new File(fileName);
		String line = "";
		
		try {
			Scanner readFile = new Scanner(fn);
			try {
				numLines = readFile.nextInt();
				readFile.nextLine();
				
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Input Mismatch");
				System.exit(1);
			}
			mi = new MediaItem[numLines];
			
			for (int i = 0; i < numLines; i ++) {
				line = readFile.nextLine();
				String ln[] = line.split("\\s*\\|\\s*");
				
					if (ln[0].equals("DVD")) {
						mi[i] = new DVD(ln[1], Double.parseDouble(ln[2]), ln[3] );
						((DVD)mi[i]).setActing(Integer.parseInt(ln[4]));
						((DVD)mi[i]).setDirecting(Integer.parseInt(ln[5]));
						((DVD)mi[i]).setSoundtrack(Integer.parseInt(ln[6]));
					}
					else if (ln[0].equals("CD")) {
						mi[i] = new CD(ln[1], Double.parseDouble(ln[2]), ln[3] );
						((CD)mi[i]).setMusic(Integer.parseInt(ln[4]));
						((CD)mi[i]).setLyrics(Integer.parseInt(ln[5]));
						((CD)mi[i]).setVocals(Integer.parseInt(ln[6]));
					}
					
				}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: ERROR");
		}
		
	}
	
	/**Sorts the array in ascending order
	 * 
	 */
	public void sortAsc() {
		Arrays.sort(mi);
	}
	
	/**Sorts the array in descending order
	 * 
	 */
	public void sortDesc() {
		Arrays.sort(mi,Collections.reverseOrder());
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#fullListByPrice()
	 */
	@Override
	public String fullListByPrice() {
		list.delete(0,list.length());
		list.append("Sorted by Price \n" +
				"================================ \n");
		Arrays.sort(mi,new comparePrice());
		
			for(int i = 0; i < numLines; i++) {
				if (i < 9)
					list.append((i+1) + ")  " + mi[i].toString() + "\n");
				else
					list.append((i+1) + ") " + mi[i].toString() + "\n");
			}
		
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see MediaLibraryModel#fullListByTitle()
	 */
	@Override
	public String fullListByTitle() {
		list.delete(0,list.length());
		list.append("Sorted by Title \n" +
				"================================ \n");
		Arrays.sort(mi,new CompareTitle());
		
			for(int i = 0; i < numLines; i++) {
				if (i < 9)
					list.append((i+1) + ")  " + mi[i].toString() + "\n");
				else
					list.append((i+1) + ") " + mi[i].toString() + "\n");
			}
		
		return list.toString();
	}
}


