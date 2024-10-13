package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.BookDao;
import com.exceptions.DuplicateEntryException;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.model.BookComment;
import com.model.BookLike;
import com.model.BookShare;
import com.service.BookService;
import com.utils.MailUtility;

@RestController
@RequestMapping("/api/book/")
@CrossOrigin
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private MailUtility mailUtility;

	@PostMapping("list")
	public Response bookList(@RequestBody PaginationBO pagination,
			@RequestHeader(value = "timezone", required = false) String timezone) {
		Response response = new Response();
		try {
			response.setListCount(bookDao.bookListCountAdminPanel(pagination));
			response.setResult(bookDao.bookListAdminPanel(pagination, timezone));
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("Book List Get Sucessfully..");
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "bookList()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
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
			mailUtility.sendExceptionEmailToDeveloper(e, "addBook()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-multiple")
	public Response addMultipleBook(@RequestBody List<Book> bookList) {
		Response response = new Response();
		try {
			return bookService.addMultipleBook(bookList);

		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "addMultipleBook()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("like")
	public Response likeBook(@RequestBody BookLike bookLike) {
		Response response = new Response();
		try {
			return bookService.bookLike(bookLike);
		} catch (DuplicateEntryException de) {
			mailUtility.sendExceptionEmailToDeveloper(de, "likeBook()");
			response.setStatus(ErrorConstants.ALREADY_EXIST);
			response.setMessage(de.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			mailUtility.sendExceptionEmailToDeveloper(e, "likeBook()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-edit-comment")
	public Response commentBook(@RequestBody BookComment bookComment) {
		Response response = new Response();
		try {
			return bookService.bookComment(bookComment);
		} catch (RequiredFieldsMissingException rme) {
			mailUtility.sendExceptionEmailToDeveloper(rme, "commentBook()");
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
		} catch (NotFoundException nfe) {
			mailUtility.sendExceptionEmailToDeveloper(nfe, "commentBook()");
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
		} catch (Exception e) {
			mailUtility.sendExceptionEmailToDeveloper(e, "commentBook()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@DeleteMapping("delete-comment")
	public Response deleteComment(@PathVariable Long commentId) {
		Response response = new Response();
		try {
			return bookService.deleteBookComment(commentId);
		} catch (RequiredFieldsMissingException rme) {
			mailUtility.sendExceptionEmailToDeveloper(rme, "deleteComment()");
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
		} catch (NotFoundException nfe) {
			mailUtility.sendExceptionEmailToDeveloper(nfe, "deleteComment()");
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
		} catch (Exception e) {
			mailUtility.sendExceptionEmailToDeveloper(e, "deleteComment()");
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("share")
	public Response shareBook(@RequestBody BookShare bookShare) {
		Response response = new Response();
		try {
			return bookService.bookShare(bookShare);
		} catch (RequiredFieldsMissingException rme) {
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
			mailUtility.sendExceptionEmailToDeveloper(rme, "shareBook()");
		} catch (NotFoundException nfe) {
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
			mailUtility.sendExceptionEmailToDeveloper(nfe, "shareBook()");
		} catch (Exception e) {
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			mailUtility.sendExceptionEmailToDeveloper(e, "shareBook()");
		}
		return response;
	}

	@PostMapping("get-by-id/{bookId}")
	public Response getBookById(@PathVariable Long bookId) {
		Response response = new Response();
		try {
			return bookService.getBookById(bookId);

		} catch (RequiredFieldsMissingException rme) {
			response.setStatus(ErrorConstants.BAD_REQUEST);
			response.setMessage(rme.getMessage());
			mailUtility.sendExceptionEmailToDeveloper(rme, "getBookById()");
		} catch (NotFoundException nfe) {
			response.setStatus(ErrorConstants.NOT_FOUND);
			response.setMessage(nfe.getMessage());
			mailUtility.sendExceptionEmailToDeveloper(nfe, "getBookById()");
		} catch (Exception e) {
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
			mailUtility.sendExceptionEmailToDeveloper(e, "getBookById()");
		}
		return response;
	}
}
