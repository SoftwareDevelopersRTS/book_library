package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ObjectDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helper.AppConstants;
import com.model.CommonAppSetting;
import com.model.ProfilePicData;
import com.model.User;

@Component
public class FileUtility {

	@Autowired
	private ObjectDao objectDao;

	public void saveProfileImages(User user) {
		if (user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
			CommonAppSetting imageStoreBaseUrl = objectDao.getObjectByParam(CommonAppSetting.class, "settingName",
					AppConstants.IMAGE_BASE_PATH);
			CommonAppSetting profileFolder = objectDao.getObjectByParam(CommonAppSetting.class, "settingName",
					AppConstants.PROFILE_IMAGE_FOLDER);

			if (null != imageStoreBaseUrl && imageStoreBaseUrl.getSettingValue() != null && null != profileFolder
					&& profileFolder.getSettingValue() != null) {
				// String directory = "C:\\Users\\DELL\\Desktop\\Book Lib Project Personal
				// Frontend\\book_library_admin_panel\\src\\assets\\profileImages\\";

				File directoryFile = new File(imageStoreBaseUrl.getSettingValue() + profileFolder.getSettingValue());
				if (!directoryFile.exists()) {
					directoryFile.mkdirs();
				}
				String fileName = "profile_" + user.getUserId() + "_" + System.currentTimeMillis() + ".png";
				String filePath = imageStoreBaseUrl.getSettingValue() + profileFolder.getSettingValue() + fileName;
				File file = new File(filePath);
				try (FileOutputStream fos = new FileOutputStream(file)) {
					String base64Image = user.getProfileImage();
					if (base64Image.startsWith("data:image/png;base64,")) {
						base64Image = base64Image.replace("data:image/png;base64,", "");
					} else if (base64Image.startsWith("data:image/jpeg;base64,")) {
						base64Image = base64Image.replace("data:image/jpeg;base64,", "");
					}
					byte[] imageBytes = Base64.getDecoder().decode(base64Image);
					fos.write(imageBytes);
					fos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				ProfilePicData profilePicData = new ProfilePicData();
				profilePicData.setProfilePicPath(fileName);
				profilePicData.setUser(user);
				objectDao.saveObject(profilePicData);
			}
		}
	}

	public String saveBase64Image(String base64Image, String type) {
		CommonAppSetting imageStoreBaseUrl = objectDao.getObjectByParam(CommonAppSetting.class, "settingName",
				AppConstants.IMAGE_BASE_PATH);

		CommonAppSetting profileFolder = objectDao.getObjectByParam(CommonAppSetting.class, "settingName", type);
		if (null != imageStoreBaseUrl && imageStoreBaseUrl.getSettingValue() != null && null != profileFolder
				&& profileFolder.getSettingValue() != null) {
			System.out.println("Inside Fav block");
			File directoryFile = new File(imageStoreBaseUrl.getSettingValue() + profileFolder.getSettingValue());
			if (!directoryFile.exists()) {
				directoryFile.mkdirs();
				System.out.println("director created Fav block");
			}
			String fileName = "image" + "_" + System.currentTimeMillis() + ".png";
			String filePath = imageStoreBaseUrl.getSettingValue() + profileFolder.getSettingValue() + fileName;
			File file = new File(filePath);
			try (FileOutputStream fos = new FileOutputStream(file)) {
				if (base64Image.startsWith("data:image/png;base64,")) {
					base64Image = base64Image.replace("data:image/png;base64,", "");
				} else if (base64Image.startsWith("data:image/jpeg;base64,")) {
					base64Image = base64Image.replace("data:image/jpeg;base64,", "");
				}else if (base64Image.startsWith("data:image/webp;base64,")) {
					base64Image = base64Image.replace("data:image/webp;base64,", "");
				}
				byte[] imageBytes = Base64.getDecoder().decode(base64Image);
				fos.write(imageBytes);
				fos.flush();
			} catch (IOException e) {
				System.out.println("Exception Ocucre");
				return null;
			}

			return fileName;
		}

		return null;
	}

	private static final ObjectMapper objectMapper = new ObjectMapper();

	// Utility method to convert List<String> to JSON String
	public String convertListToJson(List<String> list) {
		try {
			return objectMapper.writeValueAsString(list); // Convert List to JSON String
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // Handle this exception as needed
			return null;
		}
	}
}
