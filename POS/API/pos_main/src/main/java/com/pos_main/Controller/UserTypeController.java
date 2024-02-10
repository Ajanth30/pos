package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserTypeDto;
import com.pos_main.Service.UserTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:39:27 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("userType")
public class UserTypeController {
	
	@Autowired
	UserTypeService userTypeService;
	
	@PostMapping("/save")
	public ResponseDto saveUserType(@RequestBody UserTypeDto userTypeDto) {
		log.info("UserTypeController.save() invoked");
		return userTypeService.saveUserType(userTypeDto);
	}

}
