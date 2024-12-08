package com.dao;

import java.util.List;

import com.bo.PaginationBO;
import com.model.SystemUser;

public interface SecurityUserDao {
	
	List<SystemUser> employeeList(PaginationBO pagination) throws Exception;

	Long employeeListCount(PaginationBO pagination) throws Exception;

}
