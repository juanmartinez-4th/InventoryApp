package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemVO implements Serializable {
	
	private static final long serialVersionUID = 3921526142581277429L;

	private Integer id;
	
	private String code;
	
	private String description;
	
	private String detail;
	
	private UnitOfMeasureVO unitOfMeasure;
	
	private ItemLocationVO location;

	private BigDecimal cost;
	
	private BigDecimal salePrice;
	
	private BigDecimal rentPrice;
	
	private Integer existence;
	
	private String defaultPicture;
	
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

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the rentPrice
	 */
	public BigDecimal getRentPrice() {
		return rentPrice;
	}

	/**
	 * @param rentPrice the rentPrice to set
	 */
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}

	/**
	 * @return the existence
	 */
	public Integer getExistence() {
		return existence;
	}

	/**
	 * @param existence the existence to set
	 */
	public void setExistence(Integer existence) {
		this.existence = existence;
	}

	/**
	 * @return the defaultPicture
	 */
	public String getDefaultPicture() {
		return defaultPicture;
	}

	/**
	 * @param defaultPicture the defaultPicture to set
	 */
	public void setDefaultPicture(String defaultPicture) {
		this.defaultPicture = defaultPicture;
	}

	/**
	 * @return the location
	 */
	public ItemLocationVO getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(ItemLocationVO location) {
		this.location = location;
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
