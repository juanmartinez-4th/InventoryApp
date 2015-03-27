package mx.com.mindbits.argos.inventory.dao.impl;

import java.util.List;

import mx.com.mindbits.argos.inventory.dao.HibernateBaseDAO;
import mx.com.mindbits.argos.inventory.dao.UserDAO;
import mx.com.mindbits.argos.inventory.entity.User;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends HibernateBaseDAO<String, User> implements UserDAO{

	private static final long serialVersionUID = -5476855606546291847L;

	@Override
	public User getUser(String userName) {
		return find(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findByName(String userName) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findUserByName");
		query.setString("userName", "%" + userName + "%");
		
		return (List<User>)query.list();
	}
	
	@Override
	public User saveUser(User user) {
		return save(user);
	}

	@Override
	public User updateUser(User userToUpdate) {
		return update(userToUpdate);
	}

	@Override
	public void deleteUser(String userName) {
		delete(userName);
	}

}
