package com.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9020189485495788306L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "pages")
	private Integer pages;

	@Column(name = "rating")
	private String rating;
	
	@Column(name="total_stock")
	private Integer totalStock;
	
	@Column(name="availalble_stock")
	private Integer availableStock;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="library_id")
	private Library library;
	

	@OneToMany(mappedBy = "book")
	private List<BookAndCategory> bookAndCategory;

}
