package com.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Credentials implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7292618436521564913L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credential_id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "credential_name", nullable = false, length = 100,unique=true)
    private String credentialName;
    
    @Column(name="extra_info1")
    private String extraInfo1;
    
    
    
	
	

}
