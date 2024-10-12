package com.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

	private Integer status;

	private String message;

	private Object result;
}
