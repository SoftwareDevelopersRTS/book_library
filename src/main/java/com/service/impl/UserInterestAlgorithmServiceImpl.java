package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

import com.dao.ObjectDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.helper.UserInterestEnums.InterestOn;
import com.model.UserInterest;
import com.model.UserInterestAlgorithm;
import com.service.UserInterestAlgorithmService;

import aj.org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserInterestAlgorithmServiceImpl implements UserInterestAlgorithmService {
	
	@Autowired
	private ObjectDao objectDao;

	// Sub
	private static final String INTERESTED_IN_BOOK = "INTERESTED_IN_BOOK";
	private static final String INTERESTED_IN_BOOK_CATEGORY = "INTERESTED_IN_BOOK_CATEGORY";
	private static final String INTERESTED_IN_LIBRARY = "INTERESTED_IN_LIBRARY";

	private static final String NOT_INTERESTED_IN_BOOK = "NOT_INTERESTED_IN_BOOK";
	private static final String NOT_INTERESTED_IN_BOOK_CATEGORY = "NOT_INTERESTED_IN_BOOK_CATEGORY";
	private static final String NOT_INTERESTED_IN_LIBRARY = "NOT_INTERESTED_IN_LIBRARY";

	public Map<String, List<Long>> createUserInterestAlgorithm(List<UserInterest> userInterestList) throws Exception {
		HashMap<String, List<Long>> subHashMap = new HashMap<>();

		ArrayList<Long> interestedInBookArrayList = new ArrayList<>();
		ArrayList<Long> interestedInBookCategoryArrayList = new ArrayList<>();
		ArrayList<Long> interestedInLibraryArrayList = new ArrayList<>();
		ArrayList<Long> notInterestedInBookArrayList = new ArrayList<>();
		ArrayList<Long> notInterestedInBookCategoryArrayList = new ArrayList<>();
		ArrayList<Long> notInterestedInLibraryArrayList = new ArrayList<>();

		for (UserInterest interest : userInterestList) {
			// Comment ,share , like , interested , will considered as insterested = true
			if (null != interest.getIsInterested() && interest.getIsInterested() == true) {
				if (null != interest.getInterestOn()) {
					if (interest.getInterestOn() == InterestOn.BOOK) {
						interestedInBookArrayList.add(interest.getBook().getBookId());
					} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
						interestedInBookCategoryArrayList.add(interest.getBookCategory().getBookCategoryId());
					} else {
						interestedInLibraryArrayList.add(interest.getLibraryId().getLibraryId());
					}
				}
			} else {
				if (interest.getInterestOn() == InterestOn.BOOK) {
					notInterestedInBookArrayList.add(interest.getBook().getBookId());
				} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
					notInterestedInBookCategoryArrayList.add(interest.getBookCategory().getBookCategoryId());
				} else {
					notInterestedInLibraryArrayList.add(interest.getLibraryId().getLibraryId());
				}

			}
		}
		subHashMap.put(INTERESTED_IN_BOOK, interestedInBookArrayList);
		subHashMap.put(INTERESTED_IN_BOOK_CATEGORY, interestedInBookCategoryArrayList);
		subHashMap.put(INTERESTED_IN_LIBRARY, interestedInLibraryArrayList);

		subHashMap.put(NOT_INTERESTED_IN_BOOK, notInterestedInBookArrayList);
		subHashMap.put(NOT_INTERESTED_IN_BOOK_CATEGORY, notInterestedInBookCategoryArrayList);
		subHashMap.put(NOT_INTERESTED_IN_LIBRARY, notInterestedInLibraryArrayList);

		return subHashMap;
	}

	@Override
	public void changeInAlgorithmAccordingToUserAction(UserInterest userInterest) throws Exception {
		try {
			UserInterestAlgorithm existingUserInterestedAlgorithm=objectDao.getObjectByParam(UserInterestAlgorithm.class,"userId", userInterest.getUserId());
			HashMap<String, List<Long>> subHashMap = null;
			if(null!=existingUserInterestedAlgorithm && null!=existingUserInterestedAlgorithm.getInterestAlgorithm()) {
				 java.lang.reflect.Type type = new TypeToken<HashMap<String, List<Long>>>() {}.getType();
				 subHashMap = new Gson().fromJson(existingUserInterestedAlgorithm.getInterestAlgorithm(), type);
				 if (null != userInterest.getIsInterested() && userInterest.getIsInterested() == true) {
						if (null != userInterest.getInterestOn()) {
							if (userInterest.getInterestOn() == InterestOn.BOOK) {
								
								
							} else if (userInterest.getInterestOn() == InterestOn.BOOKCATEGORY) {
								
							} else {
								
							}
						}
					} else {
						if (userInterest.getInterestOn() == InterestOn.BOOK) {
							
						} else if (userInterest.getInterestOn() == InterestOn.BOOKCATEGORY) {
							
						} else {
							
						}

					}
			}

		} catch (Exception e) {

		}

	}

}
