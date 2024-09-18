package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.bo.InterestAlgorithm;
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
import java.util.HashSet;
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

	public Map<String, TreeSet<Long>> createUserInterestAlgorithm(List<UserInterest> userInterestList)
			throws Exception {
		HashMap<String, TreeSet<Long>> subHashMap = new HashMap<>();

		TreeSet<Long> interestedInBookArrayList = new TreeSet<>();
		TreeSet<Long> interestedInBookCategoryArrayList = new TreeSet<>();
		TreeSet<Long> interestedInLibraryArrayList = new TreeSet<>();
		TreeSet<Long> notInterestedInBookArrayList = new TreeSet<>();
		TreeSet<Long> notInterestedInBookCategoryArrayList = new TreeSet<>();
		TreeSet<Long> notInterestedInLibraryArrayList = new TreeSet<>();

		for (UserInterest interest : userInterestList) {
			// Comment ,share , like , interested , will considered as insterested = true
			if (null != interest.getIsInterested() && interest.getIsInterested() == true) {
				if (null != interest.getInterestOn()) {
					if (interest.getInterestOn() == InterestOn.BOOK) {
						interestedInBookArrayList.add(interest.getBook().getBookId());
					} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
						interestedInBookCategoryArrayList.add(interest.getBookCategory().getBookCategoryId());
					} else {
						interestedInLibraryArrayList.add(interest.getLibrary().getLibraryId());
					}
				}
			} else {
				if (interest.getInterestOn() == InterestOn.BOOK) {
					notInterestedInBookArrayList.add(interest.getBook().getBookId());
				} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
					notInterestedInBookCategoryArrayList.add(interest.getBookCategory().getBookCategoryId());
				} else {
					notInterestedInLibraryArrayList.add(interest.getLibrary().getLibraryId());
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

	// New Thinking
//		public InterestAlgorithm createUserInterestAlgorithmV1(List<UserInterest> userInterestList) throws Exception {
//			InterestAlgorithm interestAlgorithm=new InterestAlgorithm();
//			Set<Long> favouriteBookSet=new HashSet<Long>();
//			Set<Long> favouriteBookCategory=new HashSet<Long>();
//			Set<Long> favouriteLibrary=new HashSet<Long>();
//			Set<Long> nonFavouriteBookSet=new HashSet<Long>();
//			Set<Long> nonFavouriteBookCategory=new HashSet<Long>();
//			Set<Long> nonfavouriteLibrary=new HashSet<Long>();
//			for (UserInterest interest : userInterestList) {
//				// Comment ,share , like , interested , will considered as insterested = true
//				if (null != interest.getIsInterested() && interest.getIsInterested() == true) {
//					if (null != interest.getInterestOn()) {
//						if (interest.getInterestOn() == InterestOn.BOOK) {
//							favouriteBookSet.add(interest.getBook().getBookId());
//						} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
//							favouriteBookCategory.add(interest.getBookCategory().getBookCategoryId());
//						} else {
//							favouriteLibrary.add(interest.getLibrary().getLibraryId());
//						}
//					}
//				} else {
//					if (interest.getInterestOn() == InterestOn.BOOK) {
//						nonFavouriteBookSet.add(interest.getBook().getBookId());
//					} else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
//						nonFavouriteBookCategory.add(interest.getBookCategory().getBookCategoryId());
//					} else {
//						nonfavouriteLibrary.add(interest.getLibrary().getLibraryId());
//					}
//
//				}
//			}
//			interestAlgorithm.setFavouriteBookSet(favouriteBookSet);
//			interestAlgorithm.setFavouriteBookCategory(favouriteBookCategory);
//			interestAlgorithm.setFavouriteLibrary(favouriteLibrary);
//			interestAlgorithm.setNonFavouriteBookSet(nonFavouriteBookSet);
//			interestAlgorithm.setNonFavouriteBookCategory(nonFavouriteBookCategory);
//			interestAlgorithm.setNonfavouriteLibrary(nonfavouriteLibrary);
//			return interestAlgorithm;
//		}
	public InterestAlgorithm createUserInterestAlgorithmV1(List<UserInterest> userInterestList) throws Exception {
	    InterestAlgorithm interestAlgorithm = new InterestAlgorithm();

	    // Using StringBuffer for efficient string concatenation
	    StringBuffer favouriteBookSet = new StringBuffer();
	    StringBuffer favouriteBookCategory = new StringBuffer();
	    StringBuffer favouriteLibrary = new StringBuffer();
	    StringBuffer nonFavouriteBookSet = new StringBuffer();
	    StringBuffer nonFavouriteBookCategory = new StringBuffer();
	    StringBuffer nonFavouriteLibrary = new StringBuffer();

	    for (UserInterest interest : userInterestList) {
	        // Check if user is interested
	        if (Boolean.TRUE.equals(interest.getIsInterested())) {
	            if (interest.getInterestOn() != null) {
	                if (interest.getInterestOn() == InterestOn.BOOK) {
	                    if (favouriteBookSet.length() > 0) {
	                        favouriteBookSet.append(",");
	                    }
	                    favouriteBookSet.append(interest.getBook().getBookId());
	                } else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
	                    if (favouriteBookCategory.length() > 0) {
	                        favouriteBookCategory.append(",");
	                    }
	                    favouriteBookCategory.append(interest.getBookCategory().getBookCategoryId());
	                } else {
	                    if (favouriteLibrary.length() > 0) {
	                        favouriteLibrary.append(",");
	                    }
	                    favouriteLibrary.append(interest.getLibrary().getLibraryId());
	                }
	            }
	        } else { // For non-favourite interests
	            if (interest.getInterestOn() == InterestOn.BOOK) {
	                if (nonFavouriteBookSet.length() > 0) {
	                    nonFavouriteBookSet.append(",");
	                }
	                nonFavouriteBookSet.append(interest.getBook().getBookId());
	            } else if (interest.getInterestOn() == InterestOn.BOOKCATEGORY) {
	                if (nonFavouriteBookCategory.length() > 0) {
	                    nonFavouriteBookCategory.append(",");
	                }
	                nonFavouriteBookCategory.append(interest.getBookCategory().getBookCategoryId());
	            } else {
	                if (nonFavouriteLibrary.length() > 0) {
	                    nonFavouriteLibrary.append(",");
	                }
	                nonFavouriteLibrary.append(interest.getLibrary().getLibraryId());
	            }
	        }
	    }

	    // Setting the values as comma-separated strings by converting StringBuffers to strings
	    interestAlgorithm.setFavouriteBookSet(favouriteBookSet.toString());
	    interestAlgorithm.setFavouriteBookCategory(favouriteBookCategory.toString());
	    interestAlgorithm.setFavouriteLibrary(favouriteLibrary.toString());
	    interestAlgorithm.setNonFavouriteBookSet(nonFavouriteBookSet.toString());
	    interestAlgorithm.setNonFavouriteBookCategory(nonFavouriteBookCategory.toString());
	    interestAlgorithm.setNonfavouriteLibrary(nonFavouriteLibrary.toString());

	    return interestAlgorithm;
	}


}
