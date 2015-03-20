package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.Authority;


public interface AuthoritiesDAO {
	
	List<String> getAuthoritiesCatalog();
	
	List<Authority> getUserAuthorities(String userName);
	
	Authority addUserAuthority(Authority authority);
	
	void revokeUserAuthority(Authority authority);
	
	void revokeAllUserAuthorities(String userName);
}
