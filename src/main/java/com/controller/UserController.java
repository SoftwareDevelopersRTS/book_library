package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.UserDao;
import com.helper.CommonMessages;
import com.helper.ErrorConstatnts;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {

	private final UserService userService;
	
	private final UserDao userDao;

	public UserController(UserService userService,UserDao userDao) {
		this.userService = userService;
		this.userDao= userDao;
	}

	@PostMapping("add")
	public Response addUser(@RequestBody User user) {
		Response response = new Response();
		try {
			return userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstatnts.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PutMapping("edit")
	public Response editUser(@RequestBody User user) {
		{
			Response response = new Response();
			try {
				return userService.editUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(ErrorConstatnts.INTERNAL_SERVER_ERROR);
				response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			}
			return response;
		}

	}
	@GetMapping("details-by-id/{userId}")
	public Response getUserDetailsById(@PathVariable("userId") Long userId) {
		Response response=new Response();
		try {
			return userService.getUserDetailsById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstatnts.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@PostMapping("list")
	public Response getUserList(@RequestBody PaginationBO pagination,@RequestHeader String timeZone) {
		Response response=new Response();
		try {
			response.setStatus(ErrorConstatnts.SUCESS);
			response.setListCount(userDao.getUserCount(pagination));
			response.setResult(userDao.getUserList(pagination));
			response.setMessage("Users get sucessfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstatnts.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
}
