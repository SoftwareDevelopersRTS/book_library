package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.service.UserActivityService;
import com.utils.MailUtility;

@RestController
@CrossOrigin
@RequestMapping("/api/common/")
public class UniqueController {
	
	@Autowired
	private UserActivityService userActivityService;
	
	@Autowired
	private MailUtility mailUtility;

	@PostMapping("user-activity")
	public Response userActivity(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			return userActivityService.userActivity(pagination);
		}
		catch(Exception e) {
			mailUtility.sendExceptionEmailToDeveloper(e,"userActivity()");
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);			
		}
		return response;
	}

}
