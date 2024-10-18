package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

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
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3617351095497642220L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "birth_date")
	private String birthDate;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile", unique = true)
	private String mobile;

	@Column(name = "gender")
	private String gender;

	@Column(name = "user_unique_uid", unique = true)
	private String userUniqueUID;

	@Column(name = "password", columnDefinition = "LONGTEXT")
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "user_type")
	private String userType;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "user")
	private List<UserInterest> userInterest;

	@CreationTimestamp
	@Column(name = "user_register_date")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime userRegisterDate;

	@Transient
	private String profileImage;

}
