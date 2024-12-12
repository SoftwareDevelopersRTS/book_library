package com.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Getter
@Setter
@Entity
@Table(name = "security_action")
public class SecurityAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8733159569790262205L;

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

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "module_id")
	private Module module;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_more_permission")
	private Boolean isMorePermission;

}
