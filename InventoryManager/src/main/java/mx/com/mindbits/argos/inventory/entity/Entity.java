package mx.com.mindbits.argos.inventory.entity;

import java.io.Serializable;

public interface Entity<K extends Serializable> extends Serializable {
	
	public K getId();
}