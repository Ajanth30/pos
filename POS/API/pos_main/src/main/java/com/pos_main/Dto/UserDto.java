package com.pos_main.Dto;

import lombok.Data;

/**
 * Feb 5, 2024 
 * 10:09:25 AM
 * @author Lathusan Thurairajah
 **/

@Data
public class UserDto {
	
	private Integer id;
	private String userName;
	private String password;
	private String address;
	private String emailAdress;
	private String mobileNumber;
	private Boolean isActive;
	private UserTypeDto userTypeDto;

}
