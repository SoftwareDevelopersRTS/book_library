package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;

@RestController
@RequestMapping("/api/book/")
public class BookController {

	@PostMapping("list")
	public Response bookList(@RequestBody PaginationBO pagination,@RequestHeader String timezone) {
		Response response = new Response();
		try {

		} catch (Exception e) {

		}
		return response;

	}
}
