package com.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.model.UserInterest;

public interface UserInterestAlgorithmService {
	//This method will called in that condition were the Algorithm for spefic user is not saved
	public Map<String, TreeSet<Long>> createUserInterestAlgorithm(List<UserInterest> userInterestList) throws Exception;
	
	//This will execute if Algorithm is already defined for that user but want to update
    void changeInAlgorithmAccordingToUserAction(UserInterest userInterest) throws Exception;

}
