package app.entity.apartment.sort;
import java.util.Comparator;

//Comparator for sorting apartments according to Id order
public class IdComparator implements Comparator<ISortable> {

	@Override
	public int compare(ISortable a1, ISortable a2) {
		return (int)(a1.getID() - a2.getID());			
	}

}
