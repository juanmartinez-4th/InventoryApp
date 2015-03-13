package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class ItemLocationVO implements Serializable {

	private static final long serialVersionUID = 6128419726300665724L;

	private Integer id;

	private ItemVO item;
	
	private Integer quantity;
	
	private String section;
	
	private String hall;
	
	private String rack;
	
	private String box;
	
	private ProductionVO production;

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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}


	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}


	/**
	 * @return the hall
	 */
	public String getHall() {
		return hall;
	}


	/**
	 * @param hall the hall to set
	 */
	public void setHall(String hall) {
		this.hall = hall;
	}


	/**
	 * @return the rack
	 */
	public String getRack() {
		return rack;
	}


	/**
	 * @param rack the rack to set
	 */
	public void setRack(String rack) {
		this.rack = rack;
	}


	/**
	 * @return the box
	 */
	public String getBox() {
		return box;
	}


	/**
	 * @param box the box to set
	 */
	public void setBox(String box) {
		this.box = box;
	}


	/**
	 * @return the production
	 */
	public ProductionVO getProduction() {
		return production;
	}


	/**
	 * @param production the production to set
	 */
	public void setProduction(ProductionVO production) {
		this.production = production;
	}

	@Override
	public String toString() {
		return getClass().getName()  
				+ "[id=" + getId()
				+ ", item={" +  item + "}" 
				+ ", section=" + section 
				+ ", hall=" + hall
				+ ", rack=" + rack
				+ ", box=" + box
				+ ", project=" + production + "]";
	}
	
}
