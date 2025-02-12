package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.BookCategoryDao;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.BookCategory;
import com.service.BookCategoryService;
import com.utils.MailUtility;

@RestController
@CrossOrigin
@RequestMapping("/api/bookcategory/")
public class BookCategoryController {

	private final BookCategoryService bookCategoryService;

	private final BookCategoryDao bookCategoryDao;

	private final MailUtility mailUtility;

	public BookCategoryController(BookCategoryService bookCategoryService, BookCategoryDao bookCategoryDao,
			MailUtility mailUtility) {
		this.bookCategoryService = bookCategoryService;
		this.bookCategoryDao = bookCategoryDao;
		this.mailUtility = mailUtility;
	}

	@PostMapping("add")
	public Response addBookCategory(@RequestBody BookCategory bookCategory) {
		Response response = new Response();
		try {
			 return bookCategoryService.addBookCategory(bookCategory);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "addBookCategory()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@PostMapping("edit")
	public Response editBookCategory(@RequestBody BookCategory bookCategory) {
		Response response = new Response();
		try {
			 return bookCategoryService.editBookCategory(bookCategory);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "editBookCategory()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-multiple")
	public Response addMultipleBookCategory(@RequestBody List<BookCategory> bookCategories) {
		Response response = new Response();
		try {
			return bookCategoryService.addMultipleBookCategory(bookCategories);
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "addMultipleBookCategory()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	// Not Created Completely only started
	@PostMapping("list")
	public Response getBookCategoryList(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("BooKcategory Get Sucessfullly..");
			response.setResult(bookCategoryDao.getBookCategoryList(pagination));
			response.setListCount(bookCategoryDao.getBookCategoryCount(pagination));
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "getBookCategoryList()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@GetMapping("list-all")
	public Response getAllBookCategory() {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("BooKcategory Get Sucessfullly..");
			response.setResult(bookCategoryDao.getAllBookCategoryList());
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "getBookCategoryList()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("get-by-id/{categoryId}")
	public Response getBookCategoryById(@PathVariable Long categoryId) {
		Response response = new Response();
		try {
			return bookCategoryService.getBookCategoryById(categoryId);

		} catch (RequiredFieldsMissingException rme) {
			mailUtility.sendExceptionEmailToDeveloper(rme, "getBookCategoryById()");
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
		} catch (NotFoundException nfe) {
			mailUtility.sendExceptionEmailToDeveloper(nfe, "getBookCategoryById()");
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
		} catch (Exception e) {
			mailUtility.sendExceptionEmailToDeveloper(e, "getBookCategoryById()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@GetMapping("change-book-category-status/{bookCategoryId}")
	public Response changeBookCategoryStatus(@PathVariable Long bookCategoryId) {
		Response response = new Response();
		try {
			return bookCategoryService.changeBookCategoryStatus(bookCategoryId);
		} catch (Exception e) {
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			mailUtility.sendExceptionEmailToDeveloper(e, "changeBookCategoryStatus()");
		}
		return response;
	}


}
