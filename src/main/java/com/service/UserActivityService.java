package com.service;



import com.bo.PaginationBO;
import com.bo.Response;

public interface UserActivityService {
	
	
	public Response userActivity(PaginationBO pagination) throws Exception;

}
