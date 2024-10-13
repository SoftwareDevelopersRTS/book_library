package com.helper;

import com.bo.PaginationBO;

public class Checker {

	public static Boolean paginationChecker(PaginationBO pagination) {
		return pagination.getPageNo() != null && pagination.getPageNo() > 0 && pagination.getNumPerPage() != null
				&& pagination.getNumPerPage() > 0;
	}

}
