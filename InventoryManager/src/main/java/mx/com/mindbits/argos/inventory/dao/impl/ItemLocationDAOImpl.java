package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ItemLocationDAO;
import mx.com.mindbits.argos.inventory.entity.ItemLocation;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ItemLocationDAOImpl extends HibernateBaseDAO<Integer, ItemLocation> implements ItemLocationDAO {

	private static final long serialVersionUID = 5365399900374743663L;

	@Override
	@SuppressWarnings("unchecked")
	public List<ItemLocation> getItemLocations(Integer itemId) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getItemLocations");
		query.setInteger("itemId", itemId);
		
		return (List<ItemLocation>) query.list();
	}
	
	@Override
	public List<ItemLocation> getAllLocations() {
		return list();
	}
	
	@Override
	public ItemLocation saveLocation(ItemLocation newLocation) {
		return save(newLocation);
	}

	@Override
	public ItemLocation updateLocation(ItemLocation locationToUpdate) {
		return update(locationToUpdate);
	}
	
	@Override
	public void deleteLocation(Integer id) {
		delete(id);
	}
	
}
