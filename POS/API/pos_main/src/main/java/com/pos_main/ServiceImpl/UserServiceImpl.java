package com.pos_main.ServiceImpl;

import com.pos_main.Dto.JwtResponseDto;
import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.Exception.EmailDuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dto.UserDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.UserService;
import com.pos_main.Service.BL.UserServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;
import com.pos_main.constants.ApplicationMessageConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:08:10 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ServiceUtil serviceUtil;
	
	@Autowired
	private UserServiceBL userServiceBL;
	
	@Override
	public ResponseDto registerUser(UserDto userDto) {
		log.info("UserServiceImpl.saveUser invoked.");
		ResponseDto responseDto = null;
		try {
			UserDto saveUserDto = userServiceBL.saveUser(userDto);
				log.info("User Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveUserDto);
		} catch (Exception e) {
			log.error("Exception occurs while saving User details with the message {}", e.getLocalizedMessage());
			if(e instanceof EmailDuplicationException){
				responseDto = serviceUtil.getExceptionServiceResponse(e);
			}
			else {
				responseDto=serviceUtil.getErrorServiceResponse(ApplicationMessageConstants
						.ServiceErrorMessages.EX_SAVE_USER_DETAILS);
			}


		}
		return responseDto;
	}

	@Override
	public ResponseDto login(LoginRequestDto loginDto) {
		log.info("UserServiceImpl.login().invoked");
		try{
			JwtResponseDto jwtResponseDto=userServiceBL.login(loginDto);
			return serviceUtil.getServiceResponse(jwtResponseDto);

		}catch (Exception ex){
			log.error("Exception occurs while login  with the message : {}",ex.getLocalizedMessage());
			return serviceUtil.getExceptionServiceResponse(ex);

		}
	}

}
