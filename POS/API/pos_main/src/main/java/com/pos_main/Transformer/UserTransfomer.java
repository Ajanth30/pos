package com.pos_main.Transformer;

import com.pos_main.Dto.JwtResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.User;
import com.pos_main.Dto.UserDto;

/**
 * Feb 5, 2024 10:11:02 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Component
public class UserTransfomer implements BaseTransformer<User, UserDto> {

	@Autowired
	UserTypeTransfomer userTypeTransfomer;

	@Override
	public UserDto transform(User user) {
		UserDto userDto = null;
		if (user != null) {
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setPassword(user.getPassword());
			userDto.setAddress(user.getAddress());
			userDto.setEmailAdress(user.getEmailAdress());
			userDto.setMobileNumber(user.getMobileNumber());
			userDto.setIsActive(user.getIsActive());
			if (user.getUserType() != null) {
				userDto.setUserTypeDto(userTypeTransfomer.transform(user.getUserType()));
			}
		}
		return userDto;
	}

	@Override
	public User reverseTransform(UserDto userDto) {
		User user = null;
		if (userDto != null) {
			user = new User();
			user.setId(userDto.getId());
			user.setName(userDto.getName());
			user.setPassword(userDto.getPassword());
			user.setAddress(userDto.getAddress());
			user.setEmailAdress(userDto.getEmailAdress());
			user.setMobileNumber(userDto.getMobileNumber());
			user.setIsActive(userDto.getIsActive());
			if (userDto.getUserTypeDto() != null) {
				user.setUserType(userTypeTransfomer.reverseTransform(userDto.getUserTypeDto()));
			}
		}
		return user;
	}



}
