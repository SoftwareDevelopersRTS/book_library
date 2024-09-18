package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_interest_algorithm")
@Getter
@Setter
public class UserInterestAlgorithm  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5916131474638699103L;
	@Id
	@Column(name="user_interest_algorithm_id")
	private Long userInterestAlgorithmId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="interest_algorithm",columnDefinition = "json")
	private String interestAlgorithm;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	
	
	

}
