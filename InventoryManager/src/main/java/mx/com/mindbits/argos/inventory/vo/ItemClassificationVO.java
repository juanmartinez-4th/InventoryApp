package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class ItemClassificationVO implements Serializable {
	private static final long serialVersionUID = -4285396366110194968L;

	private ItemVO item;
	
	private CategoryVO category;

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
	
}
