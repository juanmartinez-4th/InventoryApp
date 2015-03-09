package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
public class Project extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = -2257806651342812867L;

	private String name;
	
	private String description;
	
	@Id
	@Column(name="PROJECT_ID")
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
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String descripition) {
		this.description = descripition;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[id=" +  getId() + ", name=" + name + "]";
	}
	
}
