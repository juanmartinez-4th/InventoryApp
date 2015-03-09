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
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class Item extends BaseEntity<Integer> {
	private static final long serialVersionUID = 6637860018554874375L;
	
	private String code;
	
	private String description;
	
	private String detail;
	
	private UnitOfMeasure unitOfMeasure;

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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "UNIT_OF_MEASURE")
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
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

}
