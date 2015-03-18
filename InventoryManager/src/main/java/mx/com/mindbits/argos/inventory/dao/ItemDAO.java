package mx.com.mindbits.argos.inventory.dao;

import java.math.BigInteger;
import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Item;


public interface ItemDAO extends BaseDAO<Integer, Item> {
	
	Item getItem(Integer itemId);
	
	List<Item> getAllItems();
	
	List<Item> findByDescription(String itemDescription);
	
	Item saveItem(Item item);
	
	Item updateItem(Item itemToUpdate);
	
	BigInteger getNextId();
	
}
