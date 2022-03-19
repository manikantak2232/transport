package com.pixelbox.bl;

import java.util.HashMap;

import com.pixelbox.dao.SearchDAO;

public class SearchBL {
	public static HashMap<String, Object> searchUsers(String criterion, String param) {	

		HashMap<String, Object> responseParameters = new HashMap<String, Object>();
				
		try{
			responseParameters = SearchDAO.searchUsers(criterion, param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseParameters;
	}
}
