package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.Response;

@RestController
@RequestMapping("/api/dashboard/")
public class DashboardController {
	
	
	@PostMapping("all-counts")
	public Response dashboardAllCounts() {
		Response response=new Response();
		try {
			
		}catch(Exception e) {
			
		}
		return response;
		
	}

}
