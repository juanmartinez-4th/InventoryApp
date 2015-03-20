package mx.com.mindbits.argos.inventory.bsn;

import java.util.List;

import mx.com.mindbits.argos.inventory.vo.UserVO;

public interface SecurityManager {
	
	UserVO getUser(String userName);
	
	UserVO addUser(UserVO newUser);
	
	UserVO updateUser(UserVO userToUpdate);
	
	void deleteUser(String userToDelete);
	
	List<UserVO> getAllUsers();
	
	List<String> getAuthoritiesCatalog();
	
}
