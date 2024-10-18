package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profile_pic_data")
@Getter
@Setter
public class ProfilePicData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_pic_id")
	private Long profilePicId;

	@Column(name = "profile_pic_path")
	private String profilePicPath;

	@Column(name = "user_id")
	private User user;
}
