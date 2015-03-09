package mx.com.mindbits.argos.inventory.bsn;

import mx.com.mindbits.argos.inventory.vo.ItemVO;



public interface InventoryManager {
	
	void increaseExistences(int itemId, int quantityToAdd);
	
	void decreaseExistences(int itemId, int quantityToDecrease);
	
	void moveItem(int itemId, int newLocationId, int quantityMoved);
	
	void transformItem(ItemVO oldItem, ItemVO newItem, int newQuantity);
	
}
