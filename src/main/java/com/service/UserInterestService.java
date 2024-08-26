package com.service;
import java.util.List;

import com.bo.PaginationBO;
import com.model.UserInterest;
public interface UserInterestService {
	
	List<UserInterest> providedSortedUserInterestedList(PaginationBO pagination) throws Exception;

}
