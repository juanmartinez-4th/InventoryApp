package mx.com.mindbits.argos.inventory.webapp.form;

import java.io.Serializable;

import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemPictureVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;

public class ItemCaptureForm implements Serializable {
	
	private static final long serialVersionUID = -1196418383564980720L;

	private ItemVO item;
	
	private CategoryVO category;
	
	private ItemLocationVO location;
	
	private ItemPictureVO picture;

	/**
	 * @return the item
	 */
	public ItemVO getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ItemVO item) {
		this.item = item;
	}

	/**
	 * @return the category
	 */
	public CategoryVO getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(CategoryVO category) {
		this.category = category;
	}

	/**
	 * @return the location
	 */
	public ItemLocationVO getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(ItemLocationVO location) {
		this.location = location;
	}

	/**
	 * @return the picture
	 */
	public ItemPictureVO getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(ItemPictureVO picture) {
		this.picture = picture;
	}
		
}
