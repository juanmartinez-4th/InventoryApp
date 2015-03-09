package mx.com.mindbits.argos.inventory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name="AUTHORITIES", uniqueConstraints=
		@UniqueConstraint(columnNames = {"USERNAME", "AUTHORITY"})
	)
public class Authority implements Serializable {

	private static final long serialVersionUID = 4748463989785532939L;

	@EmbeddedId
	private AuthorityPK authorityPK;
	
	@Override
	public String toString() {
		return getClass().getName() + 
				"[userName=" + authorityPK.userName + 
				", authority=" + authorityPK.authority + "]";
	}

	@Embeddable
	public class AuthorityPK implements Serializable {
		private static final long serialVersionUID = -266452474105365206L;
		private String userName;
		private String authority;

	    public AuthorityPK() {}

	    public AuthorityPK(String userName, String authority) {
	        this.userName = userName;
	        this.authority = authority;
	    }

		/**
		 * @return the userName
		 */
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
	}
	
}
