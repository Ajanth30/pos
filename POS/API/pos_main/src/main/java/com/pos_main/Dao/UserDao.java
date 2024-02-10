package com.pos_main.Dao;

import com.pos_main.Domain.User;
import com.pos_main.Dto.UserDto;

/**
 * Feb 5, 2024 
 * 11:20:57 AM
 * @author Lathusan Thurairajah
 **/

public interface UserDao extends BaseDao<User>{
	
	UserDto saveUser(UserDto userDto);

	User loadByUsername(String username);

	User findByByEmail(String email);

}
