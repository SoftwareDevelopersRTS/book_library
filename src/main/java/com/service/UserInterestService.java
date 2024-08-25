package com.service;
import java.util.List;

import com.model.UserInterest;
public interface UserInterestService {
	
	List<UserInterest> providedSortedUserInterestedList(Long userId) throws Exception;

}
