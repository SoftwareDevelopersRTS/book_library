package com.externalservice;

import java.io.IOException;

public interface GoogleDriveService {

	String uploadFile(java.io.File filePath, String mimeType) throws IOException;

	String uploadFile(java.io.File filePath) throws IOException;
}
