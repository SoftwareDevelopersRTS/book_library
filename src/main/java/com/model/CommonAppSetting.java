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
@Table(name = "common_app_setting")
@Getter
@Setter
public class CommonAppSetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436134640186687168L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "common_app_setting_id")
	private Long commonAppSettingId;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "setting_name", unique = true)
	private String settingName;

	@Column(name = "setting_type")
	private String settingType;

	@Column(name = "setting_value", columnDefinition = "LONGTEXT")
	private String settingValue;

}
