package com.pos_main.constants;

public interface ApplicationMessageConstants {
	public interface ServiceErrorMessages {

		String ERR_RETRIEVE_ALL_BRANCH_DETAILS = null;
		String EX_RETRIEVE_ALL_BRANCH_DETAILS = null;
		String ERR_SAVE_RE_BRANCH_DETAILS = null;
		String EX_SAVE_RE_BRANCH_DETAILS = null;
		String ERR_RETRIEVE_BRANCH_BY_SBU_ID = null;
		String EX_RETRIEVE_BRANCH_BY_SBU_ID = null;
		
		String ERR_SAVE_USER_DETAILS = "err.save.user.details";
		String EX_SAVE_USER_DETAILS = "ex.save.user.details";
		String ERR_SAVE_USER_TYPE_DETAILS = "err.save.user.type.details";
		String EX_SAVE_USER_TYPE_DETAILS = "ex.save.user.type.details";

		//security
		String EX_JWT_INVALID="ex.jwt.invalid";
		String EX_JWT_EXPIRED="ex.jwt.expired";




	}
}
