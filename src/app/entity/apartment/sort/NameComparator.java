package app.entity.apartment.sort;

import java.util.Comparator;

//Comparator for sorting apartments according to Client Name
public class NameComparator implements Comparator<ISortable> {

	@Override
	public int compare(ISortable a1, ISortable a2) {
		return a1.get_clientName().compareTo(a2.get_clientName());
		
	}

}
