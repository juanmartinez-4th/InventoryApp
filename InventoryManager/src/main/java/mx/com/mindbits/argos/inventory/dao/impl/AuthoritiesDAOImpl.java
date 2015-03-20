package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.AuthoritiesDAO;
import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.entity.Authority;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AuthoritiesDAOImpl extends HibernateBaseDAO<Integer, Authority> implements AuthoritiesDAO {

	private static final long serialVersionUID = -1948414228656834069L;

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAuthoritiesCatalog() {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getAuthoritiesCatalog");
		
		return (List<String>)query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Authority> getUserAuthorities(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("getUserAuthorities");
		query.setString("userName", userName);
		
		return (List<Authority>)query.list();
	}

	@Override
	public Authority addUserAuthority(Authority authority) {
		return save(authority);
	}

	@Override
	public void revokeUserAuthority(Authority authority) {
		delete(authority.getId());
	}

	@Override
	public void revokeAllUserAuthorities(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteAllUserAuthorities");
		query.setString("userName", userName);
		query.executeUpdate();
	}

}
