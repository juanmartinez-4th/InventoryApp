package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTION")
public class Production extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = -3695429293257924495L;

	private String code;
	
	private String name;
	
	private String description;
	
	@Id
	@Column(name="PRODUCTION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return super.getId();
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[code=" + code + ",name=" + name + "]";
	}
}
