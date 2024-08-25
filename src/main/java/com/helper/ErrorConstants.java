package com.helper;

import org.springframework.http.HttpStatus;

public class ErrorConstants {
	
	public static final Integer SUCESS=HttpStatus.OK.value();
	
	public static final Integer NOT_FOUND=HttpStatus.NOT_FOUND.value();
	
	public static final Integer INTERNAL_SERVER_ERROR=HttpStatus.INTERNAL_SERVER_ERROR.value();
	
	public static final Integer BAD_REQUEST=HttpStatus.BAD_REQUEST.value();
	
	public static final Integer ALREADY_EXIST=HttpStatus.ALREADY_REPORTED.value();
	

}
