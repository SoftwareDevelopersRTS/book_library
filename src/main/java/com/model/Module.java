package com.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "module")
public class Module implements Serializable {

	private static final long serialVersionUID = 4684179295327788240L;

	@Id
	@Column(name = "module_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "display_name")
	private String displayName;

}
