package com.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SecurityGroupAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5425780205228560858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupActionId;

	@ManyToOne
	@JoinColumn(name = "action_id")
	private SecurityAction securityAction;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private SecurityGroup securityGroup;

}
