package com.pos_main.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.UserTypeDao;
import com.pos_main.Domain.User;
import com.pos_main.Domain.UserType;
import com.pos_main.Dto.UserTypeDto;
import com.pos_main.Transformer.UserTypeTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 1:40:18 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class UserTypeDaoImpl extends BaseDaoImpl<UserType> implements UserTypeDao {

	@Autowired
	UserTypeTransfomer userTypeTransfomer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserTypeDto saveUserType(UserTypeDto userTypeDto) {
		log.info("UserTypeDaoImpl.saveUserType() invoked.");
		UserType userType = userTypeTransfomer.reverseTransform(userTypeDto);
		UserType saveUserType = saveOrUpdate(userType);
		return userTypeTransfomer.transform(saveUserType);
	}

}
