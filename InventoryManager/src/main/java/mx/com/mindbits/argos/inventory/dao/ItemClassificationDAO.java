package mx.com.mindbits.argos.inventory.dao;

import mx.com.mindbits.argos.inventory.entity.ItemClassification;


public interface ItemClassificationDAO extends BaseDAO<Integer, ItemClassification> {
	
	ItemClassification getItemClassification(Integer itemId);
	
	ItemClassification saveClassification(ItemClassification newClassification);
	
	ItemClassification updateClassification(ItemClassification classificationToUpdate);
	
	void deleteClassification(Integer classificationId);
	
}
