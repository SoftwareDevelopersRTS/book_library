package com.dao;

import java.util.List;

import com.bo.PaginationBO;
import com.model.Library;

public interface LibraryDao {
	
	public List<Library> getLibraryList(PaginationBO pagination) throws Exception ;
	
	public Long getLibraryCount(PaginationBO pagination) throws Exception;

}
