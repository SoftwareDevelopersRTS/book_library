package com.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "system_user")
public class SystemUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413207716211685687L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_user_id")
	private Long systemUserId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "mobile", unique = true)
	private String mobile;

	@Column(name = "unique_uid", unique = true, updatable = false)
	private String uniqueUid;

	@Column(name = "password")
	private String password;

	@Column(name = "last_login_date_time")
	private LocalDateTime lastLoginDateTime;

	@Column(name = "failed_login_attempt", columnDefinition = "INTEGER DEFAULT 0")
	private Integer failedLoginAttempt;

	@Column(name = "id_lock_expiration_time")
	private LocalDateTime idLockExpirationTime;

	@Transient
	private Long roleId;

}
