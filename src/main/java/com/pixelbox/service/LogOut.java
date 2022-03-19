package com.pixelbox.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.pixelbox.bl.SellerBL;


@Path("/log/out")
public class LogOut {
	final static Logger log = Logger.getLogger(LogOut.class);
	
	/*@Context
	 private HttpServletRequest request;*/
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
   
	public HashMap<String, Object> logout() {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		final Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		responseParameters.put("message","successfully logged out..");
		return responseParameters;
	}
}
