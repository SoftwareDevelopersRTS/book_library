package com.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.LibraryDao;
import com.dao.ObjectDao;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.BookCategory;
import com.model.Library;
import com.service.LibraryService;
import com.utils.RandomCreator;
import com.utils.Utils;

@Service
public class LibraryServiceImpl implements LibraryService {

	private final ObjectDao objectDao;

	private final LibraryDao libraryDao;

	public LibraryServiceImpl(ObjectDao objectDao, DataSource dataSource, LibraryDao libraryDao) {
		this.objectDao = objectDao;
		this.libraryDao = libraryDao;
	}

	@Override
	public Response addLibrary(Library library) throws Exception {
		Response response = new Response();
		try {
			// For library name and address is required
			if (null != library && null != library.getLibraryName() && !library.getLibraryName().isEmpty()
					&& !library.getLibraryName().trim().isEmpty() && library.getAddress() != null) {

				Library existingLibraryByName = objectDao.getObjectByParam(Library.class, "libraryName",
						library.getLibraryName());
				if (null != existingLibraryByName) {
					response.setStatus(ErrorConstants.ALREADY_EXIST);
					response.setMessage("Library Name Already Exist Try With Another Name...");
				} else {
					library.setLibraryUniqueUid(RandomCreator.generateUID(AppConstants.LIBRARY_UID_PREFIX, 8));
					library.setLibraryName(Utils.normalizeAndCapitalize(library.getLibraryName()));
					library.setIsActive(true);
					objectDao.saveObject(library.getAddress());
					library.setAddress(library.getAddress());
					objectDao.saveObject(library);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Library Added Sucuessfully..");
					response.setResult(library.getLibraryId());

				}
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
	public Response addMultipleLibrary(List<Library> libraryList) throws Exception {
		Response response = new Response();
		int sucessCount = 0;
		int errorCount = 0;
		int length=0;
		try {
			if (null != libraryList && libraryList.size() > 0) {
				length=libraryList.size();
				for (Library library : libraryList) {
					response = addLibrary(library);
					if (response.getStatus() == ErrorConstants.SUCESS) {
						sucessCount++;
					} else {
						errorCount++;
					}
				}

			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}
			response.setResult(null);
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage(
					"Libraries Added Sucessfully...Total("+length+"),Sucess(" + sucessCount + "),Failure(" + errorCount + ")");
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getLibraryById(Long libraryId) throws Exception {
		Response response = new Response();
		try {
			if (null != libraryId && libraryId > 0) {
				Library library = objectDao.getObjectById(Library.class, libraryId);
				if (null != library) {
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Library Get Sucessfully..");
					response.setResult(library);
				} else {
					response.setStatus(ErrorConstants.NOT_FOUND);
					response.setMessage("Library Not Found...");
				}
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
	public List<Library> getLibraryList(PaginationBO pagination) throws Exception {
		return libraryDao.getLibraryList(pagination);
	}

	@Override
	public Long getLibraryCount(PaginationBO pagination) throws Exception {
		return libraryDao.getLibraryCount(pagination);
	}
	
	
	@Override
	public List<Library> getAllLibraryList() throws Exception {
		return objectDao.getAllRecords(Library.class);
	}

	

}
