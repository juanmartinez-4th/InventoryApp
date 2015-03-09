package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class CategoryVO implements Serializable {
	
	private static final long serialVersionUID = -8603389267132479450L;

	private Integer id;
	
	private String name;
	
	private String description;
	
	private CategoryVO parentCategory;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentCategory
	 */
	public CategoryVO getParentCategory() {
		return parentCategory;
	}

	/**
	 * @param parentCategory the parentCategory to set
	 */
	public void setParentCategory(CategoryVO parentCategory) {
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