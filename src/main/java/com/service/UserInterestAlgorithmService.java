package com.service;

import java.util.List;
import java.util.Map;

import com.model.UserInterest;

public interface UserInterestAlgorithmService {
	public Map<String, List<Long>> createUserInterestAlgorithm(List<UserInterest> userInterestList) throws Exception;

}
