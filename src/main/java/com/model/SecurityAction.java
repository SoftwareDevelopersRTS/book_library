package com.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SecurityAction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "action_id")
	private Long actionId;

	@Column(name = "action_description", columnDefinition = "LONGTEXT")
	private String actionDescription;

	@Column(name = "seq_no")
	private Integer seqNo;

	@Column(name = "action_name")
	private String actionName;

	@Column(name = "display_action_name")
	private String displayActionName;

	@Column(name = "module")
	private String module;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_more_permission")
	private Boolean isMorePermission;

}
