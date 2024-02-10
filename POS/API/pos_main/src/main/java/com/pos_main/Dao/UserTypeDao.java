package com.pos_main.Dao;

import com.pos_main.Domain.UserType;
import com.pos_main.Dto.UserTypeDto;

/**
 * Feb 5, 2024 
 * 1:40:04 PM
 * @author Lathusan Thurairajah
 **/

public interface UserTypeDao extends BaseDao<UserType>{
	
	UserTypeDto saveUserType(UserTypeDto userTypeDto);

}
