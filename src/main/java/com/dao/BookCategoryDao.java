package com.dao;

import com.bo.PaginationBO;
import com.model.BookCategory;

import java.util.List;
public interface BookCategoryDao {
	
	public List<BookCategory> getBookCategoryList(PaginationBO pagination) throws Exception;
	
	public Long getBookCategoryCount(PaginationBO pagination) throws Exception;
	

}
