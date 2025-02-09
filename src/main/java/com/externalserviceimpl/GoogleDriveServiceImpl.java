package com.externalserviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.externalservice.GoogleDriveService;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

	private final Drive drive;

	public GoogleDriveServiceImpl(Drive drive) {
		this.drive = drive;
	}

	@Override
	public String uploadFile(java.io.File filePath, String mimeType) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName(filePath.getName());

		FileContent mediaContent = new FileContent(mimeType, filePath);

		File uploadedFile = drive.files().create(fileMetadata, mediaContent).setFields("id").execute();
		String uploadedFileId = uploadedFile.getId();

		File uploadedFileMetadata = drive.files().get(uploadedFileId).setFields("id, name, parents").execute();

		List<String> parentIds = uploadedFileMetadata.getParents();
		if (parentIds == null || parentIds.isEmpty()) {
			System.out.println("The file '" + uploadedFileMetadata.getName() + "' is located in the root directory.");
		} else {
			System.out.println("The file '" + uploadedFileMetadata.getName() + "' is located in folder with ID: "
					+ parentIds.get(0));
			File parentFolder = drive.files().get(parentIds.get(0)).setFields("name").execute();
			System.out.println("Parent Folder Name: " + parentFolder.getName());
		}

		setFilePermissions(uploadedFileId);

		return uploadedFileId;
	}

	@Override
	public String uploadFile(java.io.File filePath) throws IOException {
		// Infer the MIME type using Files.probeContentType
		Path path = filePath.toPath();
		String mimeType = Files.probeContentType(path);

		if (mimeType == null) {
			// Default MIME type if unable to determine
			mimeType = "application/octet-stream";
		}

		// Call the original method with inferred MIME type
		return uploadFile(filePath, mimeType);
	}

	private void setFilePermissions(String fileId) throws IOException {
		Permission permission = new Permission();
		permission.setType("anyone");
		permission.setRole("reader");

		drive.permissions().create(fileId, permission).execute();
		System.out.println("File permissions set to public (view-only access).");
	}
}
