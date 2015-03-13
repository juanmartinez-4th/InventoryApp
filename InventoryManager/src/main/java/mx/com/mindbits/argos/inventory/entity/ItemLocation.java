package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM_LOCATION")
@NamedQueries({
	@NamedQuery(
			name="getItemLocations", 
			query="from ItemLocation where ITEM_ID = :itemId"
	)
})
public class ItemLocation extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = 7893523157317171509L;

	private Item item;
	
	private Integer quantity;
	
	private String section;
	
	private String hall;
	
	private String rack;
	
	private String box;
	
	private Production production;

	@Id
	@Column(name="LOCATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@OneToOne
	@JoinColumn(name = "ITEM_ID")
	public Item getItem() {
		return item;
	}
	
	@Column(name="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}
	
	@Column(name="SECTION")
	public String getSection() {
		return section;
	}
	
	@Column(name="HALL")
	public String getHall() {
		return hall;
	}
	
	@Column(name="RACK")
	public String getRack() {
		return rack;
	}
	
	@Column(name="BOX")
	public String getBox() {
		return box;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "PRODUCTION")
	public Production getProduction() {
		return production;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param seccion the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @param hall the hall to set
	 */
	public void setHall(String hall) {
		this.hall = hall;
	}

	/**
	 * @param rack the rack to set
	 */
	public void setRack(String rack) {
		this.rack = rack;
	}

	/**
	 * @param box the box to set
	 */
	public void setBox(String box) {
		this.box = box;
	}
	
	/**
	 * @param project the project to set
	 */
	public void setProduction(Production production) {
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
				+ ", production=" + production + "]";
	}
	
}
