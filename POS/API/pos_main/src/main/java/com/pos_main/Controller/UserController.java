package com.pos_main.Controller;

import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.constants.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserDto;
import com.pos_main.Service.UserService;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

/**
 * Feb 5, 2024 
 * 11:03:12 AM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseDto registerUser(@RequestBody UserDto userDto) {
		log.info("UserController.registerUser() invoked");
		return userService.registerUser(userDto);
	}

	@PostMapping("/login")
	public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
		log.info("UserController.getUserByName() invoked");
		return userService.login(loginRequestDto);
	}

	@PostMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdminMessage(){
		return "hi from admin";
	}

	@PostMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String getByUserMessage(){
		return "hi from user";
	}

}
