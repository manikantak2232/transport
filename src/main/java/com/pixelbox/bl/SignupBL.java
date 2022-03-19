package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.SignupDAO;
import com.pixelbox.utils.Email;
import com.pixelbox.utils.TransportGlobalUtils;
import com.pixelbox.utils.TransportTelephonyUtils;
import com.pixelbox.utils.SMS;

public class SignupBL {
	
	public static HashMap<String, Object> registerUser(HashMap<String, Object> requestParameters) {	
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		String username = requestParameters.get("username").toString();
		String digest = requestParameters.get("digest").toString();
		String email = requestParameters.get("email").toString();
		String mobile = requestParameters.get("mobile").toString();
		mobile = TransportTelephonyUtils.getDeformattedPhoneNumber(mobile);
		if(mobile.substring(0, 1).equals("0")){
			mobile = mobile.substring(1, mobile.length());
		}
		
		try{
			responseParameters = SignupDAO.registerUser(username, digest, email, mobile);
			if((Boolean) responseParameters.get("result")){
				String emailActivationHash = responseParameters.get("emailActivationHash").toString();
				String otp = responseParameters.get("otp").toString();
				
				String subject = "Your Hoop Account Activation"; 
				String message = "Dear User, " + "<br><br>" +
								"Welcome to Hoop. Your account on Hoop is successfully created." + "<br><br>" +
								"Please click the below link to activate your account." + "<br><br>" +
								"http://hoop-env.us-west-2.elasticbeanstalk.com/activate/email?"+
															"username="+username+"&hash="+emailActivationHash + "<br><br>" +
								"Best Regards" + "<br>" +
								"Hoop Team";
								
				Email emailObj = new Email("hoop.welcome@gmail.com", "Hoop$123",
						email, subject, message); 
				emailObj.createEmailMessage(); 
				emailObj.sendEmail();
				
				SMS sms = new SMS();
				sms.sendSms("cheman_shaik@rediffmail.com", "Cheman$1", otp, "TXTLCL", mobile);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
	
	public static HashMap<String, Object> activateUserWithOTP(int otp, String email) {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();

		try{
			responseParameters = SignupDAO.activateUserWithOTP(otp, email);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters; 
	}
	
}