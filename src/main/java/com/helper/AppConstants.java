package com.helper;

public class AppConstants {

	// For Random Creation
	public static final String BOOK_UID_PREFIX = "BOOK";

	public static final String BOOK_CATEGORY_UID_PREFIX = "BOOKCAT";

	public static final String USER_UID_PREFIX = "USR";

	public static final String SYSTEM_USER_PREFIX = "SYSTEM_USR";

	public static String LIBRARY_UID_PREFIX = "LIB";

	// User Types
	public static final String ADMIN = "Admin";

	public static final String NORMAL_USER = "User";

	public static final String[] DEVELOPER_EMAILS = { "vishalshinde0804@gmail.com", "vishalushinde08042001@gmail.com",
			"vishalshinde9168574933@gmail.com", "vishalshinde.rts@gmail.com" };

	public static final String SEND_EXCEPTION_MAIL_TO_DEVELOPER = "SEND_EXCEPTION_MAIL_TO_DEVELOPER";

	public static final String CREDENTIAL_NAME_FOR_EXCEPTION_MAIL = "EXCEPTION_EMAIL";

	// Image related

	public static final String IMAGE_BASE_PATH = "IMAGE_BASE_PATH";

	public static final String PROFILE_IMAGE_FOLDER = "PROFILE_IMAGE_FOLDER";

	public static final String BOOK_IMAGE_FOLDER = "BOOK_IMAGE_FOLDER";

	public static final String BOOK_CATEGORY_IMAGE_FOLDER = "BOOK_CATEGORY_IMAGE_FOLDER";

	// Numbers

	public static final Integer ZERO = 0;

	public static final Integer ONE = 1;

	public static final Integer THREE = 3;

	public static final Integer TEN = 10;

	// Setting for max login attempt
	public static final String MAX_FAILED_LOGIN_ATTEMPTS_ALLOWED = "MAX_FAILED_LOGIN_ATTEMPTS_ALLOWED";

	public static final String FAILED_LOGIN_LOCK_DURATION_IN_MINUTES = "FAILED_LOGIN_LOCK_DURATION_IN_MINUTES";

	public static final String TEMPORARY_FILE_PATH = "src/main/resources/temporary_files";

	public static final String GOOGLE_DRIVE_SERVICE_ACCOUNT_JSON = "GOOGLE_DRIVE_SERVICE_ACCOUNT_JSON";

	public static final String GOOGLE_DRIVE_APPLICATION_NAME = "Book Library New";

}
