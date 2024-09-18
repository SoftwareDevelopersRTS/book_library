package com.dao;

import com.bo.PaginationBO;
import com.model.Book;

import java.util.List;

public interface BookDao {

	
	public List<Book> getUserWiseBookList(PaginationBO pagination) throws Exception;
}
