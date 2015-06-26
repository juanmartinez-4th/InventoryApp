package mx.com.mindbits.argos.inventory.entity;

import java.io.Serializable;

public abstract class BaseEntity<K extends Serializable> implements Entity<K>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private K id;
	
	@Override
	public K getId() {
		return id;
	}

	@Override
	public void setId(K id) {
		this.id = id;
	}
}
