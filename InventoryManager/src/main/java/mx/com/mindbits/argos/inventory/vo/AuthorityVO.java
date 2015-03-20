package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;

public class AuthorityVO implements Serializable {
	
	private static final long serialVersionUID = -4418569889028133622L;
	
	private Integer id;
	
	private String userName;
	
	private String authority;

	public Integer getId() {
		return id;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
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
