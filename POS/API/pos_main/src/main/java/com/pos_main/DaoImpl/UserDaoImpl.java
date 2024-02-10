package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import com.pos_main.Domain.Branch;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.UserDao;
import com.pos_main.Domain.User;
import com.pos_main.Dto.UserDto;
import com.pos_main.Transformer.UserTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:22:09 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Autowired
	UserTransfomer userTransfomer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public UserDto saveUser(UserDto userDto) {
		log.info("UserDaoImpl.saveUser() invoked.");
		User user = userTransfomer.reverseTransform(userDto);
		User saveUser = saveOrUpdate(user);
		return userTransfomer.transform(saveUser);
	}

	@Override
	@Transactional
	public User loadByUsername(String username) {

		Criteria criteria = getCurrentSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("user.emailAdress", username));
		User user=(User)criteria.uniqueResult();
		return (User) criteria.uniqueResult();

	}

	@Override
	@Transactional
	public User findByByEmail(String email) {
		Criteria criteria = getCurrentSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("user.emailAdress", email));
		User user=(User)criteria.uniqueResult();
		return (User) criteria.uniqueResult();
	}

}
