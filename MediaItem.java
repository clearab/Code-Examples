import java.text.DecimalFormat;



public abstract class MediaItem implements Comparable {
	
	private double price;
	private String title;
	DecimalFormat twoDec = new DecimalFormat("#.00");
	
	/**Constructor for MediaItem superclass
	 * 
	 * @param t the object's title
	 * @param p the object's price
	 */
	public MediaItem(String t, double p) {
		this.title = t;
		this.price = p;
	}
	
	/**Must return the average rating as a double
	 * @return the average ratign
	 */
	public abstract double overallRating();
	
	/* Attempts to compare this and object o's ratings, returns 1 if
	 * this has a greater rating, -1 if o has a greater rating and
	 * 0 if they are equal. If an exception is thrown, -2 is returned.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param o the object to be compared to this
	 */
	public int compareTo(Object o) {
		
		try {
		if (this.overallRating() > ((MediaItem) o).overallRating())
				return 1;
		else if (this.overallRating() < ((MediaItem) o).overallRating())
			return -1;
		else
			return 0;
		
		} catch (ClassCastException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return -2;
	}
	
	public String toString() {
		return "$" + twoDec.format(this.getPrice()) + " \"" + this.getTitle() + "\" ";
		
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
