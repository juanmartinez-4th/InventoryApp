package mx.com.mindbits.argos.inventory.dao;

import mx.com.mindbits.argos.inventory.entity.User;


public interface SecurityDAO extends BaseDAO<Integer, User> {

	User getUserByLogin(String login);
}
