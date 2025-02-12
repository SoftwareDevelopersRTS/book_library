package com.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationBO {

	private Integer pageNo;

	private Integer numPerPage;

	private String orderBy;

	private String orderType;

	private String searchKey;

	private Long userId;

	private Long id;

	// Required to capture user activty like,comment ,dislike
	private Integer activityType;

	private Integer activityOn;

	private Long activityId;

	private Double interestLevel;

}
