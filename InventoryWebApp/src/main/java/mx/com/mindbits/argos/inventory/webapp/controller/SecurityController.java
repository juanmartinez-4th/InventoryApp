package mx.com.mindbits.argos.inventory.webapp.controller;

import static mx.com.mindbits.argos.common.RequestUtils.getAlertMessage;
import static mx.com.mindbits.argos.common.RequestUtils.getRememberMeTargetUrlFromSession;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.com.mindbits.argos.common.Message;
import mx.com.mindbits.argos.inventory.vo.UserVO;
import mx.com.mindbits.argos.inventory.webapp.form.ResultsFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	
	private static final String LOGIN_VIEW = "appLogin";
	
	private static final String UNAUTHORIZED_VIEW = "unauthorized";
	
	private static final String USER_ADMINISTRATION_VIEW = "adminUsers";
	
	private static final String USER_ADMINISTRATION_VIEW_TITLE = "Administración de usuarios";

	@Autowired
	private mx.com.mindbits.argos.inventory.bsn.SecurityManager securityManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			 return "redirect:/listItems";
		}else {
			return LOGIN_VIEW;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, Model model) {

		if (error != null) {
			model.addAttribute("error", "Usuario y/o contraseña incorrectos!");
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			
			if(StringUtils.hasText(targetUrl)) {
				model.addAttribute("targetUrl", targetUrl);
				model.addAttribute("loginUpdate", true);
			}
		}

		if (logout != null) {
			model.addAttribute("msg", "Ha cerrado sesión correctamente.");
		}

		return LOGIN_VIEW;
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	public String accesssDenied(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
		}
		return UNAUTHORIZED_VIEW;
	}
	
	@RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
	public String adminUsers(Model model, 
			@ModelAttribute(value="resultsFilter") ResultsFilter resultsFilter, 
			HttpServletRequest request) {
		List<UserVO> results;
		
		if(resultsFilter != null && resultsFilter.getFilter1() != null 
				&& !"".equals(resultsFilter.getFilter1().trim())) {
			String userName = resultsFilter.getFilter1().trim().toLowerCase();
			results = securityManager.getUsersByName(userName);
		}else {
			results = securityManager.getAllUsers();
		}
		
		ResultsFilter filter = new ResultsFilter();
		filter.setFilterName("adminUsers");
		model.addAttribute("resultsFilter", filter);
		
		List<String> authorities = securityManager.getAuthoritiesCatalog();
		
		model.addAttribute("usersList", results);
		model.addAttribute("authoritiesCatalog", authorities);
		model.addAttribute("user", new UserVO());
		
		Message alertMessage = getAlertMessage(request);
		if(alertMessage != null) {
			model.addAttribute("alertMsg", alertMessage);
		}
		
		model.addAttribute("pageTitle", USER_ADMINISTRATION_VIEW_TITLE);
		
		return USER_ADMINISTRATION_VIEW;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public Message saveUser(@ModelAttribute(value="user") UserVO newUser) {
		Message response;
		UserVO user = new UserVO();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		user.setUserName(newUser.getUserName());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setEnabled(newUser.getEnabled());
		
		user = securityManager.addUser(user);
		
		if(user != null) {
			response = Message.successMessage("Nuevo usuario creado", user);
		}else {
			response = Message.failMessage("No fue posible agregar el nuevo usuario, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public Message updateUser(@ModelAttribute(value="user") UserVO user) {
		UserVO userToUpdate = user;
		Message response;
		UserVO persistedUser = securityManager.getUser(user.getUserName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(!user.getPassword().equals(persistedUser.getPassword())) {
			userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		userToUpdate = securityManager.updateUser(userToUpdate);
		if(userToUpdate != null) {
			response = Message.successMessage("Usuario actualizado", userToUpdate);
		}else {
			response = Message.failMessage("No fue posible guardar el usuario, intente más tarde");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteUser(@ModelAttribute(value="username") String username) {
		Message response = Message.successMessage("Usuario eliminado", null);
		
		try {
			securityManager.deleteUser(username);
		}catch(Exception e) {
			response = Message.failMessage("No fue posible realizar la operación, intente más tarde.");
		}
		
		return response;
	}
	
}
