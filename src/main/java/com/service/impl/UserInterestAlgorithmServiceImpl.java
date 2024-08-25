package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;

import com.helper.UserInterestEnums.InterestOn;
import com.model.UserInterest;
import com.service.UserInterestAlgorithmService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserInterestAlgorithmServiceImpl implements UserInterestAlgorithmService {

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

}
