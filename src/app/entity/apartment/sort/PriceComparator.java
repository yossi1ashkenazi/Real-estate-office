package app.entity.apartment.sort;

import java.util.Comparator;

// Comparator for sorting apartments according to price
public class PriceComparator implements Comparator<ISortable>{

	@Override
	public int compare(ISortable a1, ISortable a2) {
		return (int)(a1.get_price() - a2.get_price());		
	}

}
