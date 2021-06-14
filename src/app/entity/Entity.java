package app.entity;

/**
 * Entity, any object that can be cataloged by ID
 */
public abstract class Entity implements Cloneable {
 
	protected long ID;

	/**
	 * @param ID - The Entity primary returned from the ID cannot be less then 1
	 */
	public Entity( long ID ) {
		if( ID < 1 ) {
			throw new IllegalArgumentException("ID's value cannot be less then 1 !");
		}
		this.ID = ID;
	}
	
	public long getID() {
		return ID;
	}
}
