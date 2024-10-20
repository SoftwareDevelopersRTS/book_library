package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bo.ImageDataBO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Serializable {

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

	@Column(name = "edition")
	private String edition;

	@Column(name = "book_unique_uid", unique = true)
	private String bookUniqueUid;

	@Column(name = "total_stock")
	private Integer totalStock;

	@Column(name = "availalble_stock")
	private Integer availableStock;

	@Column(name = "is_active")
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name = "library_id")
	private Library library;

	@Transient
	private List<String> hashTags;

	@Transient
	private List<Long> bookCategoryList;

	@Transient
	private ImageDataBO imageDataBo;

	@Transient
	private Long libId;

	@Transient
	private String libraryName;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@OneToOne
	@JoinColumn(name = "last_updated_by")
	private User lastUpdatedBy;

}
