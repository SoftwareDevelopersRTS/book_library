package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;

@RestController
@CrossOrigin
@RequestMapping("/api/common/")
public class UniqueController {

	@PostMapping("user-activity")
	public Response userActivity(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			
		}
		catch(Exception e) {
			
		}
		return response;
	}

}
