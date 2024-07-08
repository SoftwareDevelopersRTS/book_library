package com.dao;

import java.util.List;

import com.bo.PaginationBO;
import com.model.User;

public interface UserDao {
	
	public List<User> getUserList(PaginationBO pagination) throws Exception;
	
	public Long getUserCount(PaginationBO pagination) throws Exception;

}
