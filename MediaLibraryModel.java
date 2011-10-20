
public interface MediaLibraryModel {

	/**
	 * Loads all data from the file named fileName. The data should be stored in
	 * an array of type MediaItem. The format of the file is as follows:
	 * 
	 * The first line of the file will contain the number of items in the file.
	 * For each following line, the line will contain 7 fields separated by a
	 * '|' character which might be surrounded by spaces. The first field will
	 * be either "CD" or "DVD" indicating the kind of media item. The next two
	 * fields will be the title and price respectively. The following 4 fields
	 * will differ depending on the kind of item. For CDs, they represent
	 * artist, vocals rating, music rating, and lyrics rating respectively. For
	 * DVDs, they represent studio, acting rating, directing rating, and
	 * soundtrack rating respectively.
	 * 
	 * @param fileName
	 *            the name of the file containing the data to be loaded.
	 */
	public void loadLibrary( String fileName );
	/**
	 * Returns a list of the top ten MediaItem objects with the
	 * highest overall rating.
	 * 
	 * @return a String containing the list from highest rating to lowest, 
	 *         one object per line, each line contains the rank followed by 
	 *         the output of the toString method for the object.
	 */
	public String getTopTenList();
	/**
	 * Returns a list of all objects in the library.
	 * 
	 * @return a String containing the list of all objects, the order is 
	 *         undefined.  There is one object per line, and each line 
	 *         contains the output of the toString method for the object.
	 */
	public String getFullList();
	/**
	 * Returns a list of all CD objects in the library that have 
	 * a music rating of at least rating.
	 * 
	 * @param rating the minimum music rating to be included in the result
	 * @return a String containing the list of all CD objects with a music
	 *         rating of at least rating, the order is 
	 *         undefined.  There is one object per line, and each line 
	 *         contains the output of the toString method for the object.
	 */
	public String getCDsByMusicRating(int rating);
	/**
	 * Returns a list of all DVD objects in the library that have 
	 * a directing rating of at least rating.
	 * 
	 * @param rating the minimum directing rating to be included in the result
	 * @return a String containing the list of all DVD objects with a directing
	 *         rating of at least rating, the order is 
	 *         undefined.  There is one object per line, and each line 
	 *         contains the output of the toString method for the object.
	 */
	public String getDVDsByDirectingRating(int rating);
	/**
	 * Returns a list of MediaItems that have a price between (and
	 * including) low and high.
	 * 
	 * @param low the minimum price to be included in the result
	 * @param high the maximum price to be included in the result
	 * @return a String containing the list of all MediaItem objects 
	 *         that are priced between low and high.  The order is 
	 *         undefined.  There is one object per line, and each line 
	 *         contains the output of the toString method for the object.
	 */
	public String getItemsWithinBudget(double low, double high);
	
	/**
	 * @return a String containing the entire list of MediaItem objects
	 * 			sorted by title
	 */
	public String fullListByTitle();
	
	/**
	 * @return a String containing the entire list of MediaItem objects
	 * 			sorted by price
	 */
	public String fullListByPrice();
	
}
