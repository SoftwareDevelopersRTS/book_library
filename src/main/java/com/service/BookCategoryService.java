package com.service;

import com.bo.Response;
import com.model.BookCategory;

public interface BookCategoryService {

	
	Response addBookCategory(BookCategory bookCategory) throws Exception;
}
