package com.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="library")
public class Library  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7393379513430463785L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="library_id")
	private Long libraryId;
	
	@Column(name="library_name", unique=true,nullable = false)
	private String libraryName;
	
	@Column(name="library_unique_uid",unique=true)
	private String libraryUniqueUid;
	
	
	@OneToOne
	@JoinColumn(name="address_id", nullable = false)
	private Address address;
	
	@Column(name="rating")
	private Float rating;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="start_time")
	private Time startTime;
	
	@Column(name="end_time")
	private Time endTime;
	
	@Column(name="is_start_today")
	private Boolean isStartToday;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="description",columnDefinition = "LONGTEXT")
	private String description;
	
	
	@OneToMany(mappedBy = "library")
	private List<Book> books;
}
