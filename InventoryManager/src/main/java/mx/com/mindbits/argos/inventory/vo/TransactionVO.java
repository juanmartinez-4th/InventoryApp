package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class TransactionVO implements Serializable {
	
	private static final long serialVersionUID = 2662902427547740835L;

	private Integer id;
	
	private String name;

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
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() + ", name=" + name + "]";
	}

}
