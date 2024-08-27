package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="book_like_dislike")
@Getter
@Setter
public class BookLike {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_like_dislike_id")
	private Long bookLikeDislikeId;
	
	@Column(name="is_liked")
	private Boolean isLiked;
	
	@JoinColumn(name="book_id")
	@ManyToOne
	private Book book;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	@Transient
	private Long bookId;
	
	@Transient
	private Long userId;
	
}
