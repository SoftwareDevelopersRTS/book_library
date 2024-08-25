package com.bo;
import java.util.List;



public class UserInterestAlgorithmBO {

	//Most interest percentage id will at first
	//Like will be considered at 100% interest
	//Dislike will be considered as 0% interest
	private List<Long> sortedInterestedBookIds;
	
	//Most not interested will at first
	private List<Long> sortedNotInterestedBookIds;
	
	private List<Long> interestedBookCategory;
	
	private List<Long> notInterestedBookCategory;
	
	private List<Long> interestedLibrary;
	
	private List<Long> notInterestedLibrary;
	
	private List<Long> interestedBooksByHashTags;
	
	private List<Long> notInterstedBooksByHashTags;

	public List<Long> getSortedInterestedBookIds() {
		return sortedInterestedBookIds;
	}

	public void setSortedInterestedBookIds(List<Long> sortedInterestedBookIds) {
		this.sortedInterestedBookIds = sortedInterestedBookIds;
	}

	public List<Long> getSortedNotInterestedBookIds() {
		return sortedNotInterestedBookIds;
	}

	public void setSortedNotInterestedBookIds(List<Long> sortedNotInterestedBookIds) {
		this.sortedNotInterestedBookIds = sortedNotInterestedBookIds;
	}

	public List<Long> getInterestedBookCategory() {
		return interestedBookCategory;
	}

	public void setInterestedBookCategory(List<Long> interestedBookCategory) {
		this.interestedBookCategory = interestedBookCategory;
	}

	public List<Long> getNotInterestedBookCategory() {
		return notInterestedBookCategory;
	}

	public void setNotInterestedBookCategory(List<Long> notInterestedBookCategory) {
		this.notInterestedBookCategory = notInterestedBookCategory;
	}

	public List<Long> getInterestedLibrary() {
		return interestedLibrary;
	}

	public void setInterestedLibrary(List<Long> interestedLibrary) {
		this.interestedLibrary = interestedLibrary;
	}

	public List<Long> getNotInterestedLibrary() {
		return notInterestedLibrary;
	}

	public void setNotInterestedLibrary(List<Long> notInterestedLibrary) {
		this.notInterestedLibrary = notInterestedLibrary;
	}

	public List<Long> getInterestedBooksByHashTags() {
		return interestedBooksByHashTags;
	}

	public void setInterestedBooksByHashTags(List<Long> interestedBooksByHashTags) {
		this.interestedBooksByHashTags = interestedBooksByHashTags;
	}

	public List<Long> getNotInterstedBooksByHashTags() {
		return notInterstedBooksByHashTags;
	}

	public void setNotInterstedBooksByHashTags(List<Long> notInterstedBooksByHashTags) {
		this.notInterstedBooksByHashTags = notInterstedBooksByHashTags;
	}
	
	
	
	
	
	
	
	
	
}
