package mx.com.mindbits.argos.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="AUTHORITIES", uniqueConstraints=
	@UniqueConstraint(columnNames = {"USERNAME", "AUTHORITY"})
)
@NamedQueries({
	@NamedQuery(name="getUserAuthorities", 
				query="from Authority where username = :userName")
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "deleteAllUserAuthorities", 
				query = "delete from authorities where username = :userName"),
	@NamedNativeQuery(name = "getAuthoritiesCatalog", 
				query = "select distinct(authority) from authorities")
})
public class Authority extends BaseEntity<Integer> {

	private static final long serialVersionUID = 5998407808111846654L;
	
	private String userName;
	
	private String authority;

    /**
	 * @return the userName
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AUTHORITY_ID")
	public Integer getId() {
		return super.getId();
	}
	
    @Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the authority
	 */
	@Column(name="AUTHORITY")
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[userName=" + userName + 
				", authority=" + authority + "]";
	}
	
}
