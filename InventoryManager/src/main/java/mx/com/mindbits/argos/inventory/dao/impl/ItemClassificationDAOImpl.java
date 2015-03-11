package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ItemClassificationDAO;
import mx.com.mindbits.argos.inventory.entity.ItemClassification;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ItemClassificationDAOImpl extends HibernateBaseDAO<Integer, ItemClassification> 
		implements ItemClassificationDAO {

	private static final long serialVersionUID = 8788663843684409840L;

	@Override
	@SuppressWarnings("unchecked")
	public List<ItemClassification> getClassificationItems(Integer categoryId) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getClassificationItems");
		query.setInteger("categoryId", categoryId);
		
		return (List<ItemClassification>) query.list();
	}
	
	@Override
	public ItemClassification getItemClassification(Integer itemId) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getItemClassification");
		query.setInteger("itemId", itemId);
		
		return (ItemClassification)query.uniqueResult();
	}

	@Override
	public ItemClassification saveClassification(ItemClassification newClassification) {
		return save(newClassification);
	}

	@Override
	public ItemClassification updateClassification(
			ItemClassification classificationToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClassification(Integer classificationId) {
		// TODO Auto-generated method stub

	}

}
