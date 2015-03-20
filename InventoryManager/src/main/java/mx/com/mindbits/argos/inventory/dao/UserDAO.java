package mx.com.mindbits.argos.inventory.dao;

import java.util.List;

import mx.com.mindbits.argos.inventory.entity.User;

public interface UserDAO extends BaseDAO<String, User> {
	
	User getUser(String userName);
	
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User updateUser(User userToUpdate);
	
	void deleteUser(String userName);
	
}
