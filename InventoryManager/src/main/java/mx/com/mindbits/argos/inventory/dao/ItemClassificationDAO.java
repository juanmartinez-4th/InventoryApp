package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.ItemClassification;


public interface ItemClassificationDAO extends BaseDAO<Integer, ItemClassification> {
	
	ItemClassification getItemClassification(Integer itemId);
	
	List<ItemClassification> getClassificationItems(Integer categoryId);
	
	ItemClassification saveClassification(ItemClassification newClassification);
	
	ItemClassification updateClassification(ItemClassification classificationToUpdate);
	
	void deleteClassification(Integer classificationId);
	
}
