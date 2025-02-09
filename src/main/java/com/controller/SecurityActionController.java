package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.Response;
import com.dao.SecurityActionDao;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.utils.MailUtility;

@RestController
@RequestMapping("/api/security-action/")
public class SecurityActionController {

	@Autowired
	private MailUtility mailUtility;

	@Autowired
	private SecurityActionDao securityActionDao;

	@GetMapping("module-wise-action-list")
	private Response moduleWiseActionList() {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("Actions get sucessfully...");
			response.setResult(securityActionDao.moduleWiseActionList());
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "moduleWiseList()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

}
