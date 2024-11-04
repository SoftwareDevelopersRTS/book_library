package com.service;

import com.bo.Response;
import com.model.SystemUser;

public interface SecurityUserService {

	public Response addEditSystemUser(SystemUser systemUser) throws Exception;
}
