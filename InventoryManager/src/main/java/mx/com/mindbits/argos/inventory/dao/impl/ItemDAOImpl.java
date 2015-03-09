package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ItemDAO;
import mx.com.mindbits.argos.inventory.entity.Item;

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
	public Item saveItem(Item item) {
		return save(item);
	}
	
	@Override
	public Item updateItem(Item item) {
		return update(item);
	}

}
