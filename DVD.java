//Andrew Clear, Lab03


/**This class creates the DVD objects
 * @author aclear16
 */
public class DVD extends MediaItem {
	
	private String studio;
	private int acting;
	private int directing;
	private int soundtrack;
	
	/**The DVD constructor
	 * @param title
	 * @param price
	 * @param stu 
	 */
	public DVD(String title, double price, String stu) {
		super(title,price);
		this.studio = stu;
		
	}
	
	/* Formats the toString method to be nicely displayed in the GUI
	 * and truncates the title at 45 characters
	 * (non-Javadoc)
	 * @see MediaItem#toString()
	 */
	@Override
	public String toString() {
		String shortTitle = this.getTitle();
		if (this.getTitle().length() > 45) {
			shortTitle = shortTitle.substring(0, 42);
			shortTitle = shortTitle.concat("...");
		}
		
		return String.format("DVD [%.2f] $%-7.2f '%-45s' (acting=%s, direct=%s, soundt=%s)", this.overallRating(),
				this.getPrice(), shortTitle, this.acting, this.directing, this.soundtrack);
	}
	
	/* Calculates the average rating
	 * (non-Javadoc)
	 * @see MediaItem#overallRating()
	 */
	@Override
	public double overallRating() {
		return (double)(this.acting + this.directing + this.soundtrack) / 3;
	}

	public String getStudio() {
		return studio;
	}

	public int getActing() {
		return acting;
	}

	public int getDirecting() {
		return directing;
	}

	public int getSoundtrack() {
		return soundtrack;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public void setActing(int acting) {
		this.acting = acting;
	}

	public void setDirecting(int directing) {
		this.directing = directing;
	}

	public void setSoundtrack(int soundtrack) {
		this.soundtrack = soundtrack;
	}
	
	

}
