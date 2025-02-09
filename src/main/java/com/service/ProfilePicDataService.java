package com.service;

import com.model.ProfilePicData;
import com.model.User;

public interface ProfilePicDataService {

	ProfilePicData getUserCurrentProfilePic(User user) throws Exception;

}
