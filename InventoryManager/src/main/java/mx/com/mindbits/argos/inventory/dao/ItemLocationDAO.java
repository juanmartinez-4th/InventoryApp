package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.ItemLocation;


public interface ItemLocationDAO extends BaseDAO<Integer, ItemLocation> {
	
	List<ItemLocation> getItemLocations(Integer itemId);
	
	List<ItemLocation> getAllLocations();
	
	ItemLocation saveLocation(ItemLocation newLocation);
	
	ItemLocation updateLocation(ItemLocation locationToUpdate);
	
	void deleteLocation(Integer locationId);
	
}
