package com.service;

import com.bo.PaginationBO;
import com.bo.Response;
import com.model.SystemUser;
import com.model.SystemUser;
import java.util.List;

public interface SecurityUserService {

	Response addEditSystemUser(SystemUser systemUser) throws Exception;

	List<SystemUser> employeeList(PaginationBO pagination) throws Exception;

	Long employeeListCount(PaginationBO pagination) throws Exception;

}
