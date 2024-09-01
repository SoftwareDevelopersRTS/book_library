package com.model;

import java.io.Serializable;

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

@Entity
@Getter
@Setter
@Table(name="user_wise_roles")
public class UserWiseRoles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1855328018726853100L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_wise_role_id")
	private Long userWiseRoleId;
	
	@ManyToOne
	@JoinColumn(name="system_user_id")
	private SystemUser systemUser;
	
	@ManyToOne
	@JoinColumn(name="system_user_role")
	private SystemUserRole systemUserRole;
}
