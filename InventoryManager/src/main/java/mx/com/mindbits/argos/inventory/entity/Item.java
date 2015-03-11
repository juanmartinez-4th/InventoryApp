package mx.com.mindbits.argos.inventory.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class Item extends BaseEntity<Integer> {
	private static final long serialVersionUID = 6637860018554874375L;
	
	private String code;
	
	private String description;
	
	private String detail;
	
	private UnitOfMeasure unitOfMeasure;
	
	private BigDecimal cost;
	
	private BigDecimal salePrice;
	
	private BigDecimal rentPrice;
	
	private Integer existence;

	@Id
	@Column(name="ITEM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	@Column(name = "DETAIL", columnDefinition="TEXT")
	public String getDetail() {
		return detail;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "UNIT_OF_MEASURE")
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	@Column(name = "COST", columnDefinition="DECIMAL")
	public BigDecimal getCost() {
		return cost;
	}
	
	@Column(name = "SALE_PRICE", columnDefinition="DECIMAL")
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	@Column(name = "RENT_PRICE", columnDefinition="DECIMAL")
	public BigDecimal getRentPrice() {
		return rentPrice;
	}
	
	@Column(name = "EXISTENCE")
	public Integer getExistence() {
		return existence;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	public void setExistence(Integer existence) {
		this.existence = existence;
	}
	
}
