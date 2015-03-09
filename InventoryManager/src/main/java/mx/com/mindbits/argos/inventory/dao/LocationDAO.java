package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Location;


public interface LocationDAO extends BaseDAO<Integer, Location> {
	
	Location getLocation(Integer locationId);
	
	List<Location> getAllLocations();
	
	Location saveLocation(Location newLocation);
	
	Location updateLocation(Location locationToUpdate);
	
	void deleteLocation(Integer locationId);
	
}
