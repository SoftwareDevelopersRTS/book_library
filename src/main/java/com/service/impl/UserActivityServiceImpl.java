package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.PaginationBO;
import com.bo.Response;
import com.dao.ObjectDao;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.helper.TypeConstants;
import com.model.Book;
import com.model.BookCategory;
import com.model.Library;
import com.model.UserInterest;
import com.service.UserActivityService;

@Service
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public Response userActivity(PaginationBO pagination) throws Exception {
		Response response = new Response();
		try {
			if (null != pagination && pagination.getActivityOn() != null && null != pagination.getActivityId()
					&& null != pagination.getActivityType()) {
				UserInterest userinterest = new UserInterest();
				userinterest.setIsInterested(true);
				if (pagination.getActivityType() == TypeConstants.LIKE) {
					userinterest.setInterestLevel(TypeConstants.EXTREMELY_INTERESTED);
					
				} else if (pagination.getActivityType() == TypeConstants.SHARE) {
					userinterest.setInterestLevel(TypeConstants.MODERATE_INTEREST);
				} else if (pagination.getActivityType() == TypeConstants.COMMENT) {
					userinterest.setInterestLevel(TypeConstants.FAIRLY_INTERESTED);
				} else if (pagination.getActivityType() == TypeConstants.INTERESTED) {
					if (null != pagination.getInterestLevel()) {
						userinterest.setInterestLevel(pagination.getInterestLevel());
					} else {
						userinterest.setInterestLevel(TypeConstants.EXTREMELY_INTERESTED);
					}
				} else if (pagination.getActivityType() == TypeConstants.NOT_INTERESTED) {
					userinterest.setIsInterested(false);
					if (null != pagination.getInterestLevel()) {
						userinterest.setInterestLevel(pagination.getInterestLevel());
					} else {
						userinterest.setInterestLevel(TypeConstants.COMPLETELY_NOT_INTERESTED);
					}

				}
				if (pagination.getActivityOn() == TypeConstants.ACTION_ON_BOOK) {

					Book book = objectDao.getObjectById(Book.class, pagination.getActivityId());
					userinterest.setBook(book);
				} else if (pagination.getActivityOn() == TypeConstants.ACTION_ON_CATEGORY) {
					BookCategory bookCategory = objectDao.getObjectById(BookCategory.class, pagination.getActivityId());
					userinterest.setBookCategory(bookCategory);

				} else {
					Library library = objectDao.getObjectById(Library.class, pagination.getActivityId());
					userinterest.setLibrary(library);

				}
				
				objectDao.saveObject(userinterest);
			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
