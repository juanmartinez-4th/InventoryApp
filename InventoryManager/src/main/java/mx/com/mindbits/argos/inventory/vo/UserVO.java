package mx.com.mindbits.argos.inventory.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 8601731008305346443L;

	private String userName;
	
	private String password;
	
	private Boolean enabled;
	
	private List<AuthorityVO> authorities;
	

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}

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

	@Override
	public String toString() {
		return getClass().getName() + 
				"[userName=" + userName + 
				", enabled=" + enabled + "]";
	}

	/**
	 * @return the authoritites
	 */
	public List<AuthorityVO> getAuthorities() {
		if(authorities == null) {
			authorities = new ArrayList<AuthorityVO>(1);
		}
		return authorities;
	}
	
}
