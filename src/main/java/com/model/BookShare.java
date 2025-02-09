package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.helper.UserInterestEnums.SharedVia;

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
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="book_share")
@Getter
@Setter
public class BookShare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5822418619908298466L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_share_id")
	private Long bookShareId;
	
	@JoinColumn(name="book_id")
	@ManyToOne
	private Book book;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="shared_via")
	@Enumerated(EnumType.STRING)
	private SharedVia sharedVia; 
	
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Transient
	private Long bookId;
	
	@Transient
	private Long userId;
}
