package com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bo.Response;
import com.dao.ObjectDao;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.BookCategory;
import com.service.BookCategoryService;
import com.utils.RandomCreator;
import com.utils.Utils;

@Service
public class BookCategoryServieImpl implements BookCategoryService {

	private final ObjectDao objectDao;

	public BookCategoryServieImpl(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	@Override
	public Response addBookCategory(BookCategory bookCategory) throws Exception {
		Response response = new Response();
		try {
			if (null != bookCategory && null != bookCategory.getBookCategoryName()
					&& !bookCategory.getBookCategoryName().isEmpty()
					&& !bookCategory.getBookCategoryName().trim().isEmpty()) {
				BookCategory existingCategoryByName = objectDao.getObjectByParam(BookCategory.class, "bookCategoryName",
						bookCategory.getBookCategoryName());
				if (null != existingCategoryByName) {
					response.setStatus(ErrorConstants.ALREADY_EXIST);
					response.setMessage("Category Name Already Present..");
				} else {
					bookCategory.setBookCategoryUniqueId(
							RandomCreator.generateUID(AppConstants.BOOK_CATEGORY_UID_PREFIX, 8));
					bookCategory.setBookCategoryName(Utils.normalizeAndCapitalize(bookCategory.getBookCategoryName()));
					objectDao.saveObject(bookCategory);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Category Added Sucessfully..");
					response.setResult(bookCategory.getBookCategoryId());
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
	public Response addMultipleBookCategory(List<BookCategory> bookCategories) throws Exception {
		Response response = new Response();
		int sucessCount = 0;
		int errorCount = 0;
		try {
			if (null != bookCategories && bookCategories.size() > 0) {
				for (BookCategory bookCategory : bookCategories) {
					response = addBookCategory(bookCategory);
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
					"Book Category Added Sucessfully...Sucess(" + sucessCount + "),Failure(" + errorCount + ")");
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
