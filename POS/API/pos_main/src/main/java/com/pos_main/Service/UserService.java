package com.pos_main.Service;

import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserDto;

/**
 * Feb 5, 2024 
 * 11:05:29 AM
 * @author Lathusan Thurairajah
 **/

public interface UserService {
		
	public ResponseDto registerUser(UserDto userDto);

	public ResponseDto login(LoginRequestDto loginDto);



}
