package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.ObjectDao;
import com.exceptions.DuplicateEntryException;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.SystemUser;
import com.service.SecurityUserService;
import com.utils.RandomCreator;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {

	@Autowired
	private ObjectDao objectDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Response addEditSystemUser(SystemUser systemUser) throws Exception {
		Response response = new Response();
		try {
			if (null == systemUser || null == systemUser.getEmail() || !systemUser.getEmail().isEmpty()
					&& null == systemUser.getPassword() && !systemUser.getPassword().isEmpty()) {
				throw new NotFoundException(CommonMessages.REQUIRED_FIELD_MISSING);
			}
			if (systemUser.getRoleId() == null || systemUser.getRoleId() <= 0) {
				throw new RequiredFieldsMissingException("Please Select Role...");
			}
			if (systemUser.getSystemUserId() != null && systemUser.getSystemUserId() > 0) {
				SystemUser existingSystemUser = objectDao.getObjectById(SystemUser.class, systemUser.getSystemUserId());
			} else {
				SystemUser existingUserByEmail = objectDao.getObjectByParam(SystemUser.class, "email",
						systemUser.getEmail());
				if (null != existingUserByEmail) {
					throw new DuplicateEntryException("Email Already Registered", ErrorConstants.ALREADY_EXIST);
				}
				SystemUser existingUserByMobile = objectDao.getObjectByParam(SystemUser.class, "mobile",
						systemUser.getMobile());
				if (null != existingUserByMobile) {
					throw new DuplicateEntryException("Mobile Already Registered", ErrorConstants.ALREADY_EXIST);
				}
				systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
				systemUser.setUniqueUid(RandomCreator.generateUID(AppConstants.USER_UID_PREFIX, 8));
				objectDao.saveObject(systemUser);
				response.setMessage("The system user has been successfully created and registered.");
			}
			response.setStatus(ErrorConstants.SUCESS);

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public List<SystemUser> employeeList(PaginationBO pagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long employeeListCount(PaginationBO pagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
