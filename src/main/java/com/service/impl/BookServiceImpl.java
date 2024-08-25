package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Response;
import com.dao.ObjectDao;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.service.BookService;
import com.utils.RandomCreator;

public class BookServiceImpl implements BookService {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public Response addBook(Book book) throws Exception {
		Response response = new Response();
		try {
			if (bookNullChecker("ADD", book)) {
				book.setBookUniqueUid(RandomCreator.generateUID(AppConstants.BOOK_UID_PREFIX, 8));
				objectDao.saveObject(book);
			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response addMultipleBook(List<Book> bookList) throws Exception {
		Response response = new Response();
		Long sucess = 0L;
		Long failure = 0L;
		for (Book book : bookList) {
			try {
				response = addBook(book);
				if (response.getStatus() == ErrorConstants.SUCESS) {
					sucess++;
				} else {
					failure++;
				}

			} catch (Exception e) {

			}
		}
		response.setResult(null);
		response.setStatus(ErrorConstants.SUCESS);
		response.setMessage("Books Added Sucessfully...sucess(" + sucess + ")," + "failure(" + failure + ")");
		return response;
	}

	@Override
	public Boolean bookNullChecker(String operationType, Book book) throws Exception {
		if ("ADD".equalsIgnoreCase(operationType)) {
			return null != book;
		} else {
			return null != book && null != book.getBookId();
		}
	}

}
