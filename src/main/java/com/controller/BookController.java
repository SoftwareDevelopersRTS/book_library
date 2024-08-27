package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.exceptions.DuplicateEntryException;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.model.BookComment;
import com.model.BookLike;
import com.service.BookService;

@RestController
@RequestMapping("/api/book/")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("list")
	public Response bookList(@RequestBody PaginationBO pagination, @RequestHeader String timezone) {
		Response response = new Response();
		try {

		} catch (Exception e) {

		}
		return response;

	}

	@PostMapping("add")
	public Response addBook(@RequestBody Book book) {
		Response response = new Response();
		try {
			return bookService.addBook(book);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-multiple")
	public Response addBook(@RequestBody List<Book> bookList) {
		Response response = new Response();
		try {
			return bookService.addMultipleBook(bookList);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@PostMapping("like")
	public Response likeBook(@RequestBody BookLike bookLike) {
		Response response =new Response();
		try {
			return bookService.bookLike(bookLike);
		}
		catch(DuplicateEntryException de) {
			response.setStatus(ErrorConstants.ALREADY_EXIST);
			response.setMessage(de.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@PostMapping("add-edit-comment")
	public Response commentBook(@RequestBody BookComment bookComment) {
		Response response=new Response();
		try {
			return bookService.bookComment(bookComment);
		}catch(RequiredFieldsMissingException rme) {
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
		}
		catch(NotFoundException nfe) {
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
		}
		catch(Exception e) {
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
	
	@DeleteMapping("delete-comment")
	public Response deleteComment(@PathVariable Long commentId) {
		Response response=new Response();
		try {
			return bookService.deleteBookComment(commentId);
		}
		catch(RequiredFieldsMissingException rme) {
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
		}
		catch(NotFoundException nfe) {
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
		}
		catch(Exception e) {
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
}
