package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="UNIT_OF_MEASURE")
@NamedQueries({
	@NamedQuery(name="findUnitByName", 
				query="from UnitOfMeasure where lower(NAME) like :unitName OR "
						+ "lower(DESCRIPTION) like :unitName")
})
public class UnitOfMeasure extends BaseEntity<Integer> {
	private static final long serialVersionUID = -717754938178615942L;

	private String name;
	
	private String description;
	
	@Id
	@Column(name="UNIT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name + "]";
	}
}
