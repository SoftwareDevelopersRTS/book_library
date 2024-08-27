package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.BookCategoryDao;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.BookCategory;
import com.service.BookCategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api/bookcategory/")
public class BookCategoryController {
	
	private final BookCategoryService bookCategoryService;
	
	private final BookCategoryDao bookCategoryDao;
	
	public BookCategoryController(BookCategoryService bookCategoryService,BookCategoryDao bookCategoryDao) {
		this.bookCategoryService =bookCategoryService;
		this.bookCategoryDao= bookCategoryDao;
	}
	
	@PostMapping("add")
	public Response addBookCategory(@RequestBody BookCategory bookCategory) {
		Response response=new Response();
		try {
			return bookCategoryService.addBookCategory(bookCategory);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@PostMapping("add-multiple")
	public Response addMultipleBookCategory(@RequestBody List<BookCategory> bookCategories) {
		Response response=new Response();
		try {
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	
	//Not Created Completely only started
	@PostMapping("list")
	public Response getBookCategoryList(@RequestBody PaginationBO pagination) {
		Response response=new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("BooKcategory Get Sucessfullly..");
			response.setResult(bookCategoryDao.getBookCategoryList(pagination));
			response.setListCount(bookCategoryDao.getBookCategoryCount(pagination));
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	

}
