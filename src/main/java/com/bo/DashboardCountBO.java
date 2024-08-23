package com.bo;
import java.util.List;
public class DashboardCountBO {
	
	private Integer countSummayType;
	
	private List<AllCountBO> allCounts;

	

	public Integer getCountSummayType() {
		return countSummayType;
	}

	public void setCountSummayType(Integer countSummayType) {
		this.countSummayType = countSummayType;
	}

	public List<AllCountBO> getAllCounts() {
		return allCounts;
	}

	public void setAllCounts(List<AllCountBO> allCounts) {
		this.allCounts = allCounts;
	}
	
	

}
