package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.ItemPicture;


public interface ItemPictureDAO extends BaseDAO<Integer, ItemPicture> {
	
	ItemPicture getItemPicture(Integer itemPictureId);
	
	List<ItemPicture> getItemPictures(Integer itemId);
	
	ItemPicture saveItemPicture(ItemPicture newLocation);
	
	ItemPicture updateItemPicture(ItemPicture temPictureToUpdate);
	
	void deleteItemPicture(Integer itemPictureId);
	
}
