package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User extends BaseEntity<Integer> {

	private static final long serialVersionUID = 941787578248243075L;

	private String userName;
	
	private String password;
	
	/*private String firstName;
	
	private String lastName;
	
	private Integer status;
	
	private String email;
	
	private Date creationDate;*/
	
	private Boolean enabled;
	
//	@Id
//	@Column(name="USER_ID")
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	public Integer getId() {
//		return super.getId();
//	}

	@Id
	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	/*
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	@Column(name="STATUS")
	public Integer getStatus() {
		return status;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	
	@Column(name="CREATE_TIME")
	public Date getCreationDate() {
		return creationDate;
	}*/

	@Column(name="ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
/*	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
*/
	@Override
	public String toString() {
		return getClass().getName() + 
				"[userName=" + userName + 
				", enabled=" + enabled + "]";
	}
	
}
