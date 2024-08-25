package com.model;

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


//While adding book from admin panel will store its hastags also
@Entity
@Table(name="book_hashtags")
@Getter
@Setter
public class BookHashTags {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookHashTagId;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="hashtag")
	private String hashtag;
	
	
}
