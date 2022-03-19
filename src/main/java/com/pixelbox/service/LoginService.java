package com.pixelbox.service;

import java.util.HashMap;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.pixelbox.bl.LoginBL;
import com.pixelbox.bl.TrucksBL;

import javax.ws.rs.QueryParam;

@Path("/login")
public class LoginService {
	final static Logger log = Logger.getLogger(LoginService.class);

	
	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> authenticateUser(HashMap<String, Object> requestParameters) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String email = requestParameters.get("email").toString();
		String digest = requestParameters.get("digest").toString();
		responseParameters = LoginBL.authenticateUser(email, digest);
		return responseParameters;
	}

	@GET
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> authenticateUser1(@QueryParam("email") String email,
			@QueryParam("digest") String digest) {

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		responseParameters = LoginBL.authenticateUser(email, digest);
		return responseParameters;
	}

	@POST
	@Path("/changePassword")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> changePassword(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String email = requestParameters.get("email").toString();
		String old_Digest = requestParameters.get("old_Digest").toString();
		String digest = requestParameters.get("digest").toString();
		responseParameters = LoginBL.changePassword(email, digest, old_Digest);
		return responseParameters;
	}

	@POST
	@Path("/linkPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> linkForNewPassword(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String email = requestParameters.get("email").toString();
		String test = email;
		test = test.replaceAll("[\\[\\](){}]", "");
		String digest = requestParameters.get("digest").toString();
		responseParameters = LoginBL.linkForNewPassword(test, digest);
		return responseParameters;
	}

	@POST
	@Path("/forgotPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> forgotPassword(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		String email = requestParameters.get("email").toString();
		responseParameters = LoginBL.sentEmailForForgotPassword(email);
		return responseParameters;
	}
	
	@GET
	@Path("/user/details/get")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getLoginUserDetails() {
		
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		responseParameters = LoginBL.getLoginUserDetails();
			
		return responseParameters;
	}

}
