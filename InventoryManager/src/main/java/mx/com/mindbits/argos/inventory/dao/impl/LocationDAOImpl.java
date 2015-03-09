package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.LocationDAO;
import mx.com.mindbits.argos.inventory.entity.Location;

import org.springframework.stereotype.Repository;

@Repository
public class LocationDAOImpl extends HibernateBaseDAO<Integer, Location> implements LocationDAO {

	private static final long serialVersionUID = 5365399900374743663L;

	@Override
	public Location getLocation(Integer id) {
		return find(id);
	}
	
	@Override
	public List<Location> getAllLocations() {
		return list();
	}
	
	@Override
	public Location saveLocation(Location newLocation) {
		return save(newLocation);
	}

	@Override
	public Location updateLocation(Location locationToUpdate) {
		return update(locationToUpdate);
	}
	
	@Override
	public void deleteLocation(Integer id) {
		delete(id);
	}
	
}
