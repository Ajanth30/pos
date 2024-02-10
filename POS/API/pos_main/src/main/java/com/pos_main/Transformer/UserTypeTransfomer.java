package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.UserType;
import com.pos_main.Dto.UserTypeDto;

/**
 * Feb 5, 2024 
 * 10:18:48 AM
 * @author Lathusan Thurairajah
 **/

@Component
public class UserTypeTransfomer implements BaseTransformer<UserType, UserTypeDto>{

	@Override
	public UserTypeDto transform(UserType userType) {
		UserTypeDto userTypeDto = null;
		if (userType != null) {
			userTypeDto = new UserTypeDto();
			userTypeDto.setId(userType.getId());
			userTypeDto.setUserType(userType.getUserType());
			userTypeDto.setIsActive(userType.getIsActive());
		}
		return userTypeDto;
	}

	@Override
	public UserType reverseTransform(UserTypeDto userTypeDto) {
		UserType userType = null;
		if (userTypeDto != null) {
			userType = new UserType();
			userType.setId(userTypeDto.getId());
			userType.setUserType(userTypeDto.getUserType());
			userType.setIsActive(userTypeDto.getIsActive());
		}
		return userType;
	}

}
