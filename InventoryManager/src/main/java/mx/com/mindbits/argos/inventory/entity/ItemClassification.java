package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="ITEM_CLASSIFICATION")
@NamedQueries({
	@NamedQuery(
			name="getItemClassification", 
			query="from ItemClassification where ITEM_ID = :itemId"
	)
})
public class ItemClassification extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = -3149113578264721221L;

	private Item item;
	
	private Category category;

	@Id
	@Column(name="CLASSIFICATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ITEM_ID")
	public Item getItem() {
		return item;
	}
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() +  
				", item={" + item  + "}, category={" + category +"}]";
	}
	
}
