package com.model;

import java.io.Serializable;
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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="system_user_role")
public class SystemUserRole implements Serializable{
	
	private static final long serialVersionUID = -2532549922630242636L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="system_user_role_id")
	private Long systemUserRoleId;
	
	@Column(name="role_name",unique=true,nullable=false)
	private String roleName;
	
	@Column(name="description",columnDefinition = "LONGTEXT")
	private String description;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JoinColumn(name="created_by")
	@ManyToOne
	private SystemUser createdBy;
	
	@JoinColumn(name="last_modified_by")
	@ManyToOne
	private SystemUser lastModifiedBy;

	
}
