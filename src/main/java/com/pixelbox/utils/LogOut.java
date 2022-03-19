package com.pixelbox.utils;

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

import com.pixelbox.bl.SellerBL;


@Path("/logout")
public class LogOut {
	final static Logger log = Logger.getLogger(LogOut.class);
	
	@Context
    private HttpServletRequest request;
	@POST
	@Path("/out")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> addSellerFuel(HashMap<String, Object> requestParameters) {
		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		

		responseParameters.put("session", session) ;
		return responseParameters;
	}
}
