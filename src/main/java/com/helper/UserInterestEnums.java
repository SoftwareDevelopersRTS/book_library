package com.helper;

public class UserInterestEnums {

	public enum InterestAction {
	    LIKE,
	    DISLIKE,
	    INTERESTED,
	    NOT_INTERESTED,
	    COMMENT,
	    SHARE,
	    VIEW,
	    RATING
	}
	public enum InterestOn {
	    BOOK,
	    BOOKCATEGORY,
	    LIBRARY
	}
	
	public enum CountType {
	    COMMENT_COUNT,
	    VIEW_COUNT,
	    RATING_COUNT
	}
	
	public enum SharedVia{
		FACEBOOK,
		WHATSAPP,
		GMAIL,
		INSTAGRAM
	}
	
}

