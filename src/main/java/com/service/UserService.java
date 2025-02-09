package com.service;

import com.bo.AuthRequest;
import com.bo.AuthResponse;
import com.bo.Response;
import com.model.SystemUserRole;
import com.model.User;
import java.util.List;
public interface UserService {

	Response addUser(User user) throws Exception;
	
	Response addMultipleUser(List<User> user) throws Exception;
	
	Response editUser(User user) throws Exception;
	
	Response getUserDetailsById(Long userId) throws Exception;
	
	List<SystemUserRole> allSystemRoles() throws Exception;
	
	AuthResponse systemUserLogin(AuthRequest authRequest) throws Exception;
	
}
