package mx.com.mindbits.argos.inventory.bsn.impl;

import java.util.ArrayList;
import java.util.List;

import mx.com.mindbits.argos.inventory.bsn.SecurityManager;
import mx.com.mindbits.argos.inventory.dao.AuthoritiesDAO;
import mx.com.mindbits.argos.inventory.dao.UserDAO;
import mx.com.mindbits.argos.inventory.entity.Authority;
import mx.com.mindbits.argos.inventory.entity.User;
import mx.com.mindbits.argos.inventory.vo.AuthorityVO;
import mx.com.mindbits.argos.inventory.vo.UserVO;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityManagerImpl implements SecurityManager {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AuthoritiesDAO authoritiesDAO;

	@Autowired
	private Mapper mapper;

	public UserVO getUser(String userName) {
		UserVO result = new UserVO();
		User user = userDAO.find(userName);
		
		result = mapper.map(user, UserVO.class);
		
		List<Authority> userAuthorities = authoritiesDAO.getUserAuthorities(userName);
		List<AuthorityVO> authoritites = new ArrayList<AuthorityVO>(userAuthorities.size());
		for (Authority authority : userAuthorities) {
			AuthorityVO authorityVO = mapper.map(authority, AuthorityVO.class);
			authoritites.add(authorityVO);
		}
		
		result.getAuthorities().addAll(authoritites);
		
		return result;
	}
	
	@Override
	public List<UserVO> getAllUsers() {
		List<User> users = userDAO.getAllUsers();
		List<UserVO> results = new ArrayList<UserVO>(users.size());
		
		for (User user : users) {
			UserVO result = mapper.map(user, UserVO.class);
			
			List<Authority> userAuthorities = authoritiesDAO.getUserAuthorities(result.getUserName());
			List<AuthorityVO> authoritites = new ArrayList<AuthorityVO>(userAuthorities.size());
			for (Authority authority : userAuthorities) {
				AuthorityVO authorityVO = mapper.map(authority, AuthorityVO.class);
				authoritites.add(authorityVO);
			}
			
			result.getAuthorities().addAll(authoritites);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public UserVO addUser(UserVO newUser) {
		User user = mapper.map(newUser, User.class);
		user = userDAO.saveUser(user);
		
		List<AuthorityVO> userAuthorities = newUser.getAuthorities();
		if(userAuthorities != null) {
			for (AuthorityVO authorityVO : userAuthorities) {
				Authority authority = mapper.map(authorityVO, Authority.class);
				authority.setUserName(user.getId());
				authoritiesDAO.addUserAuthority(authority);
			}
		}
		
		return mapper.map(user, UserVO.class);
	}
	
	@Override
	public UserVO updateUser(UserVO userToUpdate) {
		UserVO result;
		User user = mapper.map(userToUpdate, User.class);
		user = userDAO.updateUser(user);
		List<Authority> userAuthorities = authoritiesDAO.getUserAuthorities(user.getId());
		List<AuthorityVO> newAuthorities = userToUpdate.getAuthorities();
		for (Authority authority : userAuthorities) {
			boolean revokeAuth = true;
			for (AuthorityVO authorityVO : newAuthorities) {
				if(authorityVO.getAuthority().equals(authority.getAuthority())) {
					revokeAuth = false;
				}
			}
			
			if(revokeAuth) {
				authoritiesDAO.revokeUserAuthority(authority);
			}
		}
		
		for(AuthorityVO authorityVO : newAuthorities) {
			boolean grantAuth = true;
			for (Authority authority : userAuthorities) {
				if(authorityVO.getAuthority().equals(authority.getAuthority())) {
					grantAuth = false;
				}
			}
			
			if(grantAuth) {
				Authority authority = new Authority();
				authority.setUserName(user.getId());
				authority.setAuthority(authorityVO.getAuthority());
				authoritiesDAO.addUserAuthority(authority);
			}
		}
		
		result = mapper.map(user, UserVO.class);
		userAuthorities = authoritiesDAO.getUserAuthorities(user.getId());
		newAuthorities.clear();
		for (Authority authority : userAuthorities) {
			AuthorityVO newAuthority = mapper.map(authority, AuthorityVO.class);
			newAuthorities.add(newAuthority);
		}
		
		return result;
	}
	
	@Override
	public void deleteUser(String userToDelete) {
		authoritiesDAO.revokeAllUserAuthorities(userToDelete);
		userDAO.deleteUser(userToDelete);
	}

	public List<String> getAuthoritiesCatalog() {
		return authoritiesDAO.getAuthoritiesCatalog();
	}
	
}
