package com.dao;

import com.bo.DashboardCountBO;
import com.bo.PaginationBO;

public interface DashboardDao {
	
	public DashboardCountBO dashboardAllCounts(PaginationBO pagination) throws Exception;

}
