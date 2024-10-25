package com.service;

import java.util.List;

import com.bo.PaginationBO;
import com.bo.Response;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.model.Library;

public interface LibraryService {

	Response addLibrary(Library library) throws Exception;

	Response addMultipleLibrary(List<Library> library) throws Exception;

	Response getLibraryById(Long libraryId) throws Exception;

	List<Library> getLibraryList(PaginationBO pagination) throws Exception;

	Long getLibraryCount(PaginationBO pagination) throws Exception;

	List<Library> getAllLibraryList() throws Exception;

	Response changeLibraryStatus(Long libraryId) throws Exception;

}
