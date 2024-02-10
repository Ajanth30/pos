package com.pos_main.Domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Feb 5, 2024 
 * 10:01:39 AM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "userName",nullable = false)
	private String name;
	@Column(name = "password",nullable = false)
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "emailAdress",nullable = false)
	private String emailAdress;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name = "isActive")
	private Boolean isActive;
	@JoinColumn(name = "userTypeId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private UserType userType;
	
}
