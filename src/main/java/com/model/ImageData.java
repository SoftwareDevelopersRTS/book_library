package com.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class ImageData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageDataId;

	@Column(name = "front_image_path")
	private String frontImagePath;
	

	@Column(name = "back_image_path")
	private String backImagePath;
	
	@Column(name="book_pdf_path")
	private String bookPdfPath;
	
	@Column(name="extra_images",columnDefinition = "json")
	private String extraImages;
	

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "book_category_id")
	private BookCategory bookCategory;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;


}
