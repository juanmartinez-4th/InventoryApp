package mx.com.mindbits.argos.inventory.dao.impl;

import java.math.BigInteger;
import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.entity.Item;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl extends HibernateBaseDAO<Integer, Item> implements ItemDAO {

	private static final long serialVersionUID = 2638865260434037416L;

	@Override
	public Item getItem(Integer id) {
		return find(id);
	}
	
	@Override
	public List<Item> getAllItems() {
		return list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Item> findByDescription(String itemDescription) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findItemByDescription");
		query.setString("itemDescription", "%" + itemDescription + "%");
		
		return (List<Item>)query.list();
	}
	
	@Override
	public BigInteger getNextId() {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getNextId");
		
		return (BigInteger)query.uniqueResult();
	}
	
	@Override
	public Item saveItem(Item item) {
		return save(item);
	}
	
	@Override
	public Item updateItem(Item item) {
		return update(item);
	}

}
