package app.entity.apartment.sort;

/**
 * 
 * An interface that requires implementing methods that are used for sorting according to certain parameters
 *
 */
public interface ISortable {
	
	public String get_clientName();
	
	public double get_price();
	
	public long getID();
}
