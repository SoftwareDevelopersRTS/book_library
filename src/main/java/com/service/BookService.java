package com.service;

import com.bo.Response;
import com.model.Book;
import com.model.BookComment;
import com.model.BookLike;
import com.model.BookShare;

import java.util.List;

public interface BookService {

	Response addBook(Book book) throws Exception;

	Response addMultipleBook(List<Book> bookList) throws Exception;

	Boolean bookNullChecker(String operationType, Book book) throws Exception;

	void saveBookAndBookCategory(List<Long> bookcategoryIdList, Book book) throws Exception;

	void saveBookHashtags(List<String> hashTags, Book book) throws Exception;

	Response bookLike(BookLike bookLike) throws Exception;

	Response bookComment(BookComment bookComment) throws Exception;

	Response deleteBookComment(Long commentId) throws Exception;

	Response bookShare(BookShare bookShare) throws Exception;

	Response getBookById(Long bookId) throws Exception;

	Response changeBookStatus(Long bookId) throws Exception;

}
