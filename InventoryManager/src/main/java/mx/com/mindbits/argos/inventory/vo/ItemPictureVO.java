package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class ItemPictureVO implements Serializable {	

	private static final long serialVersionUID = 9114517267070378117L;

	private Integer id;
	
	private ItemVO item;
	
	private String fileName;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder();
		strB.append(getClass().getName());
		strB.append("[id=" +  getId());
		strB.append(", fileName=" + fileName);
		strB.append(", item={");
		strB.append(item != null ? item.toString() : "NONE" + "}]");
		return strB.toString();
	}

}
