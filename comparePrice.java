//Andrew Clear, Lab03

import java.util.Comparator;


/** Allows the ability to sort an array of MediaItems by price
 *
 */
public class comparePrice implements Comparator{  //should have been "ComparePrice"

	/* Follows the protocol of the Comparator interface, returns -2 if incorrect
	 * object types are passed
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object a, Object b) {
		if (a instanceof MediaItem && b instanceof MediaItem) {
			if (((MediaItem)a).getPrice() > ((MediaItem)b).getPrice())
				return 1;
			else if (((MediaItem)a).getPrice() < ((MediaItem)b).getPrice())
				return -1;
			else
				return 0;
		}
		else
		return -2;
	}

}
