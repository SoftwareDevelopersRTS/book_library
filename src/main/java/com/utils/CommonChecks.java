package com.utils;

import com.bo.PaginationBO;

public class CommonChecks {

	public static Boolean paginationPageNumberCheck(PaginationBO pagination) {
		return pagination.getPageNo() != null && pagination.getPageNo() > 0
				&& pagination.getNumPerPage() != null && pagination.getNumPerPage() > 0;
	}

}
