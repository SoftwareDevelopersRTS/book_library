package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.SystemUser;
import com.service.SecurityUserService;
import com.utils.MailUtility;

@RestController
@CrossOrigin
@RequestMapping("api/security-user/")
public class SecurityUserController {

	@Autowired
	private MailUtility mailUtility;

	@Autowired
	private SecurityUserService securityUserService;

	@PostMapping("add-edit")
	public Response addSystemUser(@RequestBody SystemUser systemUser) {
		Response response = new Response();
		try {
			return securityUserService.addEditSystemUser(systemUser);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			mailUtility.sendExceptionEmailToDeveloper(e, "addSystemUser()");
		}
		return response;

	}

	@PostMapping("list")
	public Response employeeList(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("Employees get sucessfully");
			response.setListCount(securityUserService.employeeListCount(pagination));
			response.setResult(securityUserService.employeeList(pagination));

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			mailUtility.sendExceptionEmailToDeveloper(e, "employeeList()");
		}
		return response;
	}

}
