package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Item;


public interface ItemDAO extends BaseDAO<Integer, Item> {
	
	Item getItem(Integer itemId);
	
	List<Item> getAllItems();
	
	Item saveItem(Item item);
	
	Item updateItem(Item itemToUpdate);
	
}
