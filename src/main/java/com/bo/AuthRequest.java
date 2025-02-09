package com.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
	
	private String email;
	
	private String password;
	
	private Long systemUserRoleId;
	
	

}
