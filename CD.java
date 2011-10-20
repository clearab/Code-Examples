// Andrew Clear, Lab03

/**This class creates the CD objects
 * 
 */
public class CD extends MediaItem {
	
	private String artist;
	private int vocals;
	private int music;
	private int lyrics;
	
	/**The CD constructor
	 * 
	 * @param title
	 * @param price
	 * @param art
	 */
	public CD(String title, double price, String art) {
		super(title,price);
		this.artist = art;
	}
	
	/* the toString method, formatted to display nicely in the 
	 * GUI. Shortens the title if it is > 45 characters long
	 * @see MediaItem#toString()
	 */
	@Override
	public String toString() {
		String shortTitle = this.getTitle();
		if (this.getTitle().length() > 45) {
			shortTitle = shortTitle.substring(0, 42);
			shortTitle = shortTitle.concat("...");
		}
		
		return String.format("CD  [%.2f] $%-7.2f '%-45s' (vocals=%s, lyrics=%s, music=%s)", this.overallRating(),
				this.getPrice(), shortTitle, this.vocals, this.lyrics, this.music);
	}
	
	/* Calculates the average rating
	 * (non-Javadoc)
	 * @see MediaItem#overallRating()
	 */
	@Override
	public double overallRating() {
		return (double)(this.vocals + this.music + this.lyrics ) / 3;
	}


	public String getArtist() {
		return artist;
	}

	public int getVocals() {
		return vocals;
	}

	public int getMusic() {
		return music;
	}

	public int getLyrics() {
		return lyrics;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setVocals(int vocals) {
		this.vocals = vocals;
	}

	public void setMusic(int music) {
		this.music = music;
	}

	public void setLyrics(int lyrics) {
		this.lyrics = lyrics;
	}
	
}
