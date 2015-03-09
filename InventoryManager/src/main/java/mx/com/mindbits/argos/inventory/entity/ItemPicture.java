package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM_PICTURE")
public class ItemPicture extends BaseEntity<Integer> {	
	
	private static final long serialVersionUID = 1114926115986640405L;

	private Item item;
	
	private String fileName;

	@Id
	@Column(name="PICTURE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ITEM_ID")
	public Item getItem() {
		return item;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() + 
				", fileName=" + fileName + 
				", item={" + 
				item != null ? item.toString() : "NONE" + "}]";
	}
	
}
