package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.DashboardDao;
import com.helper.ErrorConstants;
import com.helper.ErrorMessages;

@RestController
@RequestMapping("/api/dashboard/")
public class DashboardController {
	
	@Autowired
	private DashboardDao dashboardDao;
	
	@PostMapping("all-counts")
	public Response dashboardAllCounts(@RequestBody PaginationBO pagination) {
		Response response=new Response();
		try {
			response.setResult(dashboardDao.dashboardAllCounts(pagination));
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage(ErrorMessages.DATA_RETRIEVED_SUCESSFULLY);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR_MESSAGE);
		}
		return response;
		
	}

}
