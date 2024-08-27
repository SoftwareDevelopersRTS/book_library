package com.service;

import com.bo.Response;
import com.model.BookCategory;
import java.util.List;
public interface BookCategoryService {

	
	Response addBookCategory(BookCategory bookCategory) throws Exception;
	
	Response addMultipleBookCategory(List<BookCategory> bookCategoreis) throws Exception;
}
