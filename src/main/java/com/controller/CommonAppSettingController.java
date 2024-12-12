package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.CommonAppSettingDao;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.utils.MailUtility;

@RestController
@RequestMapping("/api/common-app-setting/")
@CrossOrigin
public class CommonAppSettingController {

	@Autowired
	private CommonAppSettingDao commonAppSettingDao;

	@Autowired
	private MailUtility mailUtility;

	@PostMapping("list")
	public Response getAllCommonAppSetting(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("Common App Setting List Get Sucessfully...");
			response.setResult(commonAppSettingDao.getCommonAppSettingList(pagination));
			response.setListCount(commonAppSettingDao.getCommonAppSettingListCount(pagination));
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "getAllCommonAppSetting()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
}
