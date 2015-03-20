package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User extends BaseEntity<String> {

	private static final long serialVersionUID = 941787578248243075L;
	
	private String password;
	
	private Boolean enabled;
	
	@Id
	@Column(name="USERNAME")
	public String getId() {
		return super.getId();
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	
	@Column(name="ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[userName=" + getId() + 
				", enabled=" + enabled + "]";
	}
	
}
