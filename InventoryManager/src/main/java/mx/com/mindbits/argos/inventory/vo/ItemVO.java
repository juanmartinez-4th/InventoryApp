package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class ItemVO implements Serializable {
	
	private static final long serialVersionUID = 3921526142581277429L;

	private Integer id;
	
	private String code;
	
	private String description;
	
	private String detail;
	
	private UnitOfMeasureVO unitOfMeasure;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the unitOfMeasure
	 */
	public UnitOfMeasureVO getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * @param unitOfMeasure the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(UnitOfMeasureVO unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() + 
				", code=" + code +
				", description=" + description +
				", unitOfMeasure={" + 
				unitOfMeasure != null ? unitOfMeasure.toString() : "NONE" + "}]";
	}
	
}
