package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category extends BaseEntity<Integer> {	
	
	private static final long serialVersionUID = 4938217472935286123L;

	private String name;
	
	private String description;
	
	private Category parentCategory;

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	@OneToOne
	@JoinColumn(name = "PARENT_CATEGORY")
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	@Override
	public String toString() {
		String category = getClass().getName() + 
				"[id=" +  getId() + 
				", name=" + name + 
				", parentCategory={";
		if(parentCategory != null) {
			return category + parentCategory.toString() + "}]";
		}else {
			return category + "NONE}]";
		}
	}

}