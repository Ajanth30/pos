package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: BranchService.java. Company: www.softmacs.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

public interface BranchService {

	ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	ResponseDto gellAllBranches();
	
	public ResponseDto save(BranchDto branchDto);

	ResponseDto gellAllBranchesBySbuId(Integer sbuId);

}
