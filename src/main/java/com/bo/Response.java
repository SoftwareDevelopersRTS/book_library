package com.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	
	private Integer status;
	
	private String message;
	
	private Long listCount;
	
	private Object result;
	

	

}
