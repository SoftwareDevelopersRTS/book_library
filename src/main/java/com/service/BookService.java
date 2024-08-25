package com.service;

import com.bo.Response;
import com.model.Book;
import java.util.List;
public interface BookService {
	
	Response addBook(Book book) throws Exception;
	
	Response addMultipleBook(List<Book> bookList) throws Exception;
	
	Boolean bookNullChecker(String operationType,Book book) throws Exception;
	
	void saveBookAndBookCategory(List<Long> bookcategoryIdList,Book book) throws Exception;
	
	void saveBookHashtags(List<String> hashTags,Book book) throws Exception;

}
