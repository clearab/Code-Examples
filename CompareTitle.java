//Andrew Clear
import java.util.Comparator;

/**Allows an array of MediaItem objects to be sorted by price
 *
 */
public class CompareTitle implements Comparator{

	@Override
	public int compare(Object a, Object b) {
		if (a instanceof MediaItem && b instanceof MediaItem)
			return (((MediaItem) a).getTitle()).compareTo(((MediaItem) b).getTitle());
		else
			return -2;
	}

}
