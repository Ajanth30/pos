package com.pos_main.Service.BL;

import java.security.Principal;
import java.util.ArrayList;

import com.pos_main.Domain.CustomUserDetails;
import com.pos_main.Domain.User;
import com.pos_main.Dto.JwtResponseDto;
import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.Exception.EmailDuplicationException;
import com.pos_main.Service.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.UserDao;
import com.pos_main.Dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:17:32 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;



	public UserDto saveUser(UserDto userDto) {
		log.info("UserServiceBL.saveUser() invoked.");
		User user=userDao.findByByEmail(userDto.getEmailAdress());
		if(user!=null){
			log.info("exception thrown because of email duplication");
			throw new EmailDuplicationException("user with the email id "+userDto.getEmailAdress()+" already exists");

		}else {
			String encodedPassword=passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(encodedPassword);
			return userDao.saveUser(userDto);
		}

	}
	public User getUserByUserName(String username){
		log.info("UserServiceBL.getUserByUserName() invoked");
		return userDao.loadByUsername(username);
	}

	public JwtResponseDto login(LoginRequestDto request){
		log.info("UserServiceBL.login() invoked");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		if(authentication.isAuthenticated()){
			CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
			return JwtResponseDto.builder()
					.accessToken(jwtUtil.GenerateToken(userDetails))
					.build();
		} else {
			throw new RuntimeException("authentication failed");
		}
	}

}
