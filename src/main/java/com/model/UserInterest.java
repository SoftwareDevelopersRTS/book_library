package com.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_interest")
@Getter
@Setter
//This table is simple usefull to show user wise data in user is interseted in any category or book then will show above in lisy
//in case user in not interested then will not show or will show at last
public class UserInterest  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8969079430906345134L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_interest_id")
	private Long userInterestId;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	@JoinColumn(name="book_id")
	@ManyToOne
	private Book book;
	
	@JoinColumn(name="book_category_id")
	@ManyToOne
	private BookCategory bookCategory;
	
	@Column(name="is_interested")
	private Boolean isInterested;
	
	
	
	
}
