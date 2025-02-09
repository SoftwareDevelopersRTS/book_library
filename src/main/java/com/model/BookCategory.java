package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.bo.ImageDataBO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book_category")
public class BookCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1445382166664204533L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_category_id")
	private Long bookCategoryId;

	@Column(name = "book_category_unique_id", unique = true)
	private String bookCategoryUniqueId;

	@Column(name = "book_category_name", unique = true)
	private String bookCategoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "category_image_path")
	private String categoryImagePath;

	@Column(name = "is_active")
	private Boolean isActive;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Transient
	private ImageDataBO imageDataBo;

}
