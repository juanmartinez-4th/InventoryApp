package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.ItemPictureDAO;
import mx.com.mindbits.argos.inventory.entity.ItemPicture;

import org.springframework.stereotype.Repository;

@Repository
public class ItemPictureDAOImpl extends HibernateBaseDAO<Integer, ItemPicture> implements ItemPictureDAO {

	private static final long serialVersionUID = 6567261838336406933L;

	@Override
	public ItemPicture getItemPicture(Integer id) {
		return find(id);
	}
	
	@Override
	public List<ItemPicture> getAllItemPictures() {
		return list();
	}
	
	@Override
	public ItemPicture saveItemPicture(ItemPicture itemPicture) {
		return save(itemPicture);
	}

	@Override
	public ItemPicture updateItemPicture(ItemPicture pictureToUpdate) {
		return update(pictureToUpdate);
	}
	
	@Override
	public void deleteItemPicture(Integer id) {
		delete(id);
	}
	
}
