package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ObjectDao;
import com.model.ProfilePicData;
import com.model.User;
import com.service.ProfilePicDataService;

@Service
public class ProfilePicDataServiceImpl implements ProfilePicDataService {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public ProfilePicData getUserCurrentProfilePic(User user) throws Exception {

		return objectDao.getFirstRecordOrderedBy(ProfilePicData.class, "user", user, "createdAt", false);

	}

}
