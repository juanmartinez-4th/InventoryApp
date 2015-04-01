package mx.com.mindbits.argos.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestUtils {

	public static boolean isRememberMeAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null) {
			return false;
		}
 
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}
	
	public static void setRememberMeTargetUrlToSession(HttpServletRequest request, String url){
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.setAttribute("targetUrl", url);
		}
	}
	
	public static Message getAlertMessage(HttpServletRequest request) {
		Message message = null;
		boolean error = "1".equals((String) request.getParameter("error"));
		boolean success = "1".equals((String) request.getParameter("success"));
		String msg = (String) request.getParameter("msg");
		
		if(error || success) {
			if(msg == null || "".equals(msg)) {
				if(error) {
					msg = "No fue posible completar la operación";
				}else if(success){
					msg = "Operación exitosa";
				}
			}
			if(error) {
				message = Message.failMessage(msg);
			}else if(success){
				message = Message.successMessage(msg, null);
			}
		}
		
		return message;
	}
	
	public static String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl") == null ? 
					"" : session.getAttribute("targetUrl").toString();
		}
		
		return targetUrl;
	}

	public static String getCurrentUser() {
		String userName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			userName = authentication.getName();
		}else {
			userName = "NOT AUTHENTICATED";
		}
		
		return userName;
	}
	
}
