package com.service;

import com.bo.Response;
import com.model.User;
import java.util.List;
public interface UserService {

	Response addUser(User user) throws Exception;
	
	Response addMultipleUser(List<User> user) throws Exception;
	
	Response editUser(User user) throws Exception;
	
	Response getUserDetailsById(Long userId) throws Exception;
	
}
