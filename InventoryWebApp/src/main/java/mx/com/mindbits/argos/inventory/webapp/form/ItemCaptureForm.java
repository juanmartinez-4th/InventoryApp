package mx.com.mindbits.argos.inventory.webapp.form;

import java.io.Serializable;
import java.util.List;

import mx.com.mindbits.argos.inventory.vo.CategoryVO;
import mx.com.mindbits.argos.inventory.vo.ItemLocationVO;
import mx.com.mindbits.argos.inventory.vo.ItemVO;

import org.springframework.web.multipart.MultipartFile;

public class ItemCaptureForm implements Serializable {
	
	private static final long serialVersionUID = -1196418383564980720L;

	private ItemVO item;
	
	private CategoryVO category;
	
	private ItemLocationVO location;
	
	private List<MultipartFile> pictureFiles;
	
	private List<String> itemPictures;
	
	private Boolean redirectNew;
	
	private String itemBarcode;

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
	 * @return the pictureFiles
	 */
	public List<MultipartFile> getPictureFiles() {
		return pictureFiles;
	}

	/**
	 * @param pictureFiles the pictureFiles to set
	 */
	public void setPictureFiles(List<MultipartFile> pictureFiles) {
		this.pictureFiles = pictureFiles;
	}

	/**
	 * @return the redirectNew
	 */
	public Boolean getRedirectNew() {
		return redirectNew;
	}

	/**
	 * @param redirectNew the redirectNew to set
	 */
	public void setRedirectNew(Boolean redirectNew) {
		this.redirectNew = redirectNew;
	}

	/**
	 * @return the itemPictures
	 */
	public List<String> getItemPictures() {
		return itemPictures;
	}

	/**
	 * @param itemPictures the itemPictures to set
	 */
	public void setItemPictures(List<String> itemPictures) {
		this.itemPictures = itemPictures;
	}

	/**
	 * @return the itemBarcode
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * @param itemBarcode the itemBarcode to set
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}

}
