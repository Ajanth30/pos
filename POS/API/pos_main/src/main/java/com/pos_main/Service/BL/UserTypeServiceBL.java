package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.UserTypeDao;
import com.pos_main.Dto.UserTypeDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:41:08 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserTypeServiceBL {
	
	@Autowired
	UserTypeDao userTypeDao;
	
	public UserTypeDto saveUserType(UserTypeDto userTypeDto) {
		log.info("UserTypeServiceBL.saveUserType() invoked.");
		return userTypeDao.saveUserType(userTypeDto);
	}


}
