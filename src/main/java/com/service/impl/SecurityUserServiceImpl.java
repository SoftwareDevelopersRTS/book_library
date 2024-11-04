package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Response;
import com.dao.ObjectDao;
import com.exceptions.NotFoundException;
import com.helper.CommonMessages;
import com.model.SystemUser;
import com.service.SecurityUserService;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public Response addEditSystemUser(SystemUser systemUser) throws Exception {
		Response response = new Response();
		try {
			if (null == systemUser || null == systemUser.getEmail() || !systemUser.getEmail().isEmpty()
					&& null == systemUser.getPassword() && !systemUser.getPassword().isEmpty()) {
				throw new NotFoundException(CommonMessages.REQUIRED_FIELD_MISSING);
			}
			if (systemUser.getSystemUserId() != null && systemUser.getSystemUserId() > 0) {
				SystemUser existingSystemUser = objectDao.getObjectById(SystemUser.class, systemUser.getSystemUserId());
			} else {
				SystemUser existingUserByEmail = objectDao.getObjectByParam(SystemUser.class, "email",
						systemUser.getEmail());
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
