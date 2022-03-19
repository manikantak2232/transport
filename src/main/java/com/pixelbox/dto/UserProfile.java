package com.pixelbox.dto;

import java.util.List;



public class UserProfile {

	public Integer userId;
	public String firstName;
	public String lastName;
	public String middleName;
	public String email;
	public String password;
	public String mobile;
	public boolean isLocked;
	public int lockedCount;
	public boolean isFirstTimeLogin;
	public String token;
	public List<String> roles;
	public List<String> permissions;
	//public List<PartnerProfile> profiles;
	public List<String> relativeUrls;
	
	
	
}
