package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.BranchDao;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchBL.java. Company: www.softmacs.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Service
public class BranchServiceBL {

	@Autowired
	BranchDao branchDao;

	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BranchServiceBL.getAll()invoked");
		return branchDao.getAll(pageNumber, pageSize, searchParams);
	}

	public List<BranchDto> gellAllBranches() {
		log.info("BranchServiceBL.gellAllBranches() invoked");
		return branchDao.gellAllBranches();

	}
	
	public BranchDto save(BranchDto branchDto) {
		log.info("BranchServiceBL.save() invoked.");
		return branchDao.save(branchDto);
	}

	public List<BranchDto> getbranchBySBUid(Integer sbuId) {
		log.info("BranchServiceBL.getAllbySbuId()invoked");
		return branchDao.getbranchBySBUid(sbuId);
	}

}
