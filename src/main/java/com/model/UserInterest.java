package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.helper.UserInterestEnums.InterestOn;
import com.helper.UserInterestEnums.CountType;
import com.helper.UserInterestEnums.InterestAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_interest")
@Getter
@Setter
//This table is simple usefull to show user wise data in user is interseted in any category or book then will show above in lisy
//in case user in not interested then will not show or will show at last
//Will manage complete user interest in single table 
//
public class UserInterest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8969079430906345134L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_interest_id")
	private Long userInterestId;

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;

	@JoinColumn(name = "book_id")
	@ManyToOne
	private Book book;

	@Enumerated(EnumType.STRING)
	@Column(name = "interest_action")
	private InterestAction interestAction;

	@Column(name = "interested_level")
	private Double interestLevel;

	@Enumerated(EnumType.STRING)
	@Column(name = "interest_on")
	private InterestOn interestOn;

	@JoinColumn(name = "book_category_id")
	@ManyToOne
	private BookCategory bookCategory;
	
	@JoinColumn(name = "library_id")
	@ManyToOne
	private Library libraryId;

	@Column(name = "is_interested")
	private Boolean isInterested;
	
	
	
	//This will simply usefull for getting how much times user viewed or commented on current category or book
	@Column(name="count")
	private Integer count;

	@Enumerated(EnumType.STRING)
	@Column(name="count_type")
	private CountType countType;
	

	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
}
