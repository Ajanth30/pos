package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserTypeDto;
import com.pos_main.Service.UserTypeService;
import com.pos_main.Service.BL.UserTypeServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;
import com.pos_main.constants.ApplicationMessageConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:41:22 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserTypeServiceImpl implements UserTypeService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	UserTypeServiceBL userTypeServiceBL;
	
	@Override
	public ResponseDto saveUserType(UserTypeDto UserTypeDto) {
		log.info("UserTypeServiceImpl.saveUser invoked.");
		ResponseDto responseDto = null;
		try {
			UserTypeDto saveUserTypeDto = userTypeServiceBL.saveUserType(UserTypeDto);
			if (saveUserTypeDto != null) {
				log.info("UserType Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveUserTypeDto);
			} else {
				log.info("Unable to save User Type details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_USER_TYPE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving User Type details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER_TYPE_DETAILS);
		}
		return responseDto;
	}

}
