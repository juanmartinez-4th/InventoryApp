package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class ProjectVO implements Serializable {
	
	private static final long serialVersionUID = 8773942711802715499L;

	private Integer id;

	private String name;
	
	private String description;

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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() + ", name=" + name + "]";
	}
		
}
