package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.AuthRequest;
import com.bo.AuthResponse;
import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.UserDao;
import com.exceptions.NotFoundException;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.User;
import com.service.UserService;
import com.utils.MailUtility;

import java.util.List;

@RestController
@RequestMapping("api/user/")
@CrossOrigin
public class UserController {

	private final UserService userService;

	private final UserDao userDao;

	private final MailUtility mailUtility;

	public UserController(UserService userService, UserDao userDao, MailUtility mailUtility) {
		this.userService = userService;
		this.userDao = userDao;
		this.mailUtility = mailUtility;
	}

	@PostMapping("add")
	public Response addUser(@RequestBody User user) {
		Response response = new Response();
		try {
			return userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "addUser()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-multiple")
	public Response addMutltipleUser(@RequestBody List<User> user) {
		Response response = new Response();
		try {
			return userService.addMultipleUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "addMutltipleUser()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
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
				mailUtility.sendExceptionEmailToDeveloper(e, "editUser()");
				response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
				response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			}
			return response;
		}

	}

	@GetMapping("details-by-id/{userId}")
	public Response getUserDetailsById(@PathVariable("userId") Long userId) {
		Response response = new Response();
		try {
			return userService.getUserDetailsById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "getUserDetailsById()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("list")
	public Response getUserList(@RequestBody PaginationBO pagination, @RequestHeader String timeZone) {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setListCount(userDao.getUserCount(pagination));
			response.setResult(userDao.getUserList(pagination));
			response.setMessage("Users get sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "getUserList()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@GetMapping("all-security-roles")
	public Response allSecurityRoles() {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setResult(userService.allSystemRoles());
			response.setMessage("All Roles get sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "allSecurityRoles()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("login")
	private AuthResponse systemUserLogin(@RequestBody AuthRequest authRequest) {
		AuthResponse response = new AuthResponse();
		try {
			return userService.systemUserLogin(authRequest);
		} catch (NotFoundException notFoundException) {
			mailUtility.sendExceptionEmailToDeveloper(notFoundException, "allSecurityRoles()");
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(notFoundException.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "allSecurityRoles()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
}
