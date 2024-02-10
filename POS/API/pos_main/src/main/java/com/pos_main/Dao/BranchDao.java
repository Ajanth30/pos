package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Branch;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;

/**
 * Title: BranchDao.java. Company: www.softmacs.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

public interface BranchDao extends BaseDao<Branch> {

	PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	List<BranchDto> gellAllBranches();
	
	BranchDto save (BranchDto branchDto);

	List<BranchDto> getbranchBySBUid(Integer sbuId);

}
