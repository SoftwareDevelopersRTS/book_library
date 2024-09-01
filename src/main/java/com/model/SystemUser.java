package com.model;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="system_user")
public class SystemUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413207716211685687L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="system_user_id")
	private Long systemUserId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email",unique=true)
	private String email;
	
	@Column(name="mobile",unique=true)
	private String mobile;
	
	@Column(name="unique_uid",unique=true,updatable=false)
	private String uniqueUid;
	
	
	
	
}
