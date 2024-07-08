package com.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookAndCategory  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7886013792011352464L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_and_category_relation_id")
	private Long bookAndBookCategoryId;
	
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory bookCategory;
	
	
	
}
