
package com.configuration;

import org.springframework.context.annotation.Configuration;

import com.dao.ObjectDao;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.helper.AppConstants;
import com.model.CommonAppSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class GoogleDriveConfig {

	@Autowired
	private ObjectDao objectDao;

	public String getGoogleDriveCredentialsJSON() {
		CommonAppSetting commonAppSetting = objectDao.getObjectByParam(CommonAppSetting.class, "settingName",
				AppConstants.GOOGLE_DRIVE_SERVICE_ACCOUNT_JSON);
		;
		if (null != commonAppSetting && commonAppSetting.getSettingValue() != null) {
			return commonAppSetting.getSettingValue();
		} else {
			return null;
		}
	}

	@Bean
	Drive googleDrive() throws IOException {
		String jsonFile = getGoogleDriveCredentialsJSON();
		if (null != jsonFile) {
			System.out.println("Google Drive Json File is not null");
			ByteArrayInputStream credentialsStream = new ByteArrayInputStream(jsonFile.getBytes());

			GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
					.createScoped(Collections.singleton(DriveScopes.DRIVE));

			return new Drive.Builder(new com.google.api.client.http.javanet.NetHttpTransport(),
					new com.google.api.client.json.jackson2.JacksonFactory(), new HttpCredentialsAdapter(credentials))
					.setApplicationName(AppConstants.GOOGLE_DRIVE_APPLICATION_NAME).build();
		} else {
			return null;
		}
	}
}
