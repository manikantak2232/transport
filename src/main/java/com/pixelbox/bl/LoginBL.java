package com.pixelbox.bl;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.pixelbox.dao.LoginDAO;
import com.pixelbox.dao.TrucksDAO;
import com.pixelbox.dao.UserDetailsDAO;
import com.pixelbox.utils.Email;

import org.apache.shiro.session.Session;

public class LoginBL {

	final static Logger log = Logger.getLogger(LoginBL.class);

	public static HashMap<String, Object> authenticateUser(String email, String digest) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		boolean result = true;
		int errorCode = 0;
		String errorMessage = "";
		Session session = null;

		final Subject currentUser = SecurityUtils.getSubject();
		final UsernamePasswordToken token = new UsernamePasswordToken(email, digest);
		System.out.println("token " + token.toString());
		try {
			currentUser.login(token);
			session = currentUser.getSession(true);
			// responseParameters.put("message","You have successfully logged
			// in.");

			responseParameters = LoginDAO.getUserFullName(email);

			// *********** code added for getting user details start
			// ****************
			// HashMap<String, Object> userDetails =
			// UserDetailsDAO.getUserDetails();
			// responseParameters.put("User Details", userDetails);
			// *********** code added for getting user details end
			// ****************

		} catch (UnknownAccountException uae) {
			result = false;
			errorCode = 1101;
			errorMessage = "Username is not available in the system";
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(errorMessage);
			}
		} catch (IncorrectCredentialsException ice) {
			result = false;
			errorCode = 1102;
			errorMessage = "Password didn't match, please try again";
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(errorMessage);
			}

		} catch (LockedAccountException lae) {
			result = false;
			errorCode = 1103;
			errorMessage = "Account for that username is locked - can't login";
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(errorMessage);
			}
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			result = false;
			errorCode = 1104;
			errorMessage = "Authentication Exception, Please try again";
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(errorMessage);
			}
		} catch (Exception eX) {
			result = false;
			errorCode = 1105;
			errorMessage = "Un-known Exception Condition, Please try again";
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(errorMessage);
			}
		}

		responseParameters.put("result", result);
		responseParameters.put("errorCode", errorCode);
		responseParameters.put("errorMessage", errorMessage);

		return responseParameters;
	}

	public static HashMap<String, Object> changePassword(String email, String digest, String old_Digest) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = LoginDAO.changePassword(email, digest, old_Digest);
			if ((Boolean) responseParameters.get("result")) {

				responseParameters.put("message", "Your password has been successfully changed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseParameters;
	}

	public static HashMap<String, Object> linkForNewPassword(String email, String digest) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try {
			responseParameters = LoginDAO.linkForNewPassword(email, digest);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> sentEmailForForgotPassword(String email) {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
			try{
				responseParameters = LoginDAO.sentEmailForForgotPassword(email);
				if((Boolean) responseParameters.get("result")){
					
			//		String otp = responseParameters.get("otp").toString();
					 email=responseParameters.get("email").toString();
					String subject = "Transport account notification."; 
					String message = "Dear User, " + "<br><br>" +
									"please click on the link, to reset your password." + "<br>" +
									"http://localhost:8080/app/views/link_for_forgot_password.html?"+
								//	"<input type=hidden id=email value="+email+">"+
									"email="+email+"<br><br>" +
									
									"Best Regards," + "<br>" +
									"Hoop Team";
									
					Email emailObj = new Email("hoop.welcome@gmail.com", "Hoop$123",
							email, subject, message); 
					emailObj.createEmailMessage(); 
					emailObj.sendEmail();
					responseParameters.remove("email");
			
					responseParameters.put("message", "Link successfully sent" );
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return responseParameters;
		}
	
	public static HashMap<String, Object> getLoginUserDetails() {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		try{
			responseParameters = LoginDAO.getLoginUserDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return responseParameters;
		
	}
}
