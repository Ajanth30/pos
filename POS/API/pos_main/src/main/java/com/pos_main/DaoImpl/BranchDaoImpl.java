package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.BranchDao;
import com.pos_main.Domain.Branch;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.BranchTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchDaoImpl.java. Company: www.softmacs.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Repository
public class BranchDaoImpl extends BaseDaoImpl<Branch> implements BranchDao {

	@Autowired
	BranchTransformer branchTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BranchDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Branch> allBranchList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM branch";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);
		
		if (pageSize == 0) {
			pageSize = count;
		}
		
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "Branch");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allBranchList = criteria.list();
		recordCount = allBranchList.size();
		if (allBranchList != null && !allBranchList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allBranchList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allBranchList.stream().map(Invoice -> {
				return branchTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

	@Override
	public List<BranchDto> gellAllBranches() {
		log.info("BranchDaoImpl.gellAllBranches() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class);
		List<BranchDto> branchDtoList = null;
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (branchList != null && !branchList.isEmpty()) {
			branchDtoList = new ArrayList<>();
			for (Branch branch : branchList) {
				branchDtoList.add(branchTransformer.transform(branch));
			}
		}
		return branchDtoList;
	}

	@Override
	@Transactional
	public BranchDto save(BranchDto branchDto) {
		log.info("BranchDaoImpl.save() invoked.");
		Branch branch = branchTransformer.reverseTransform(branchDto);
		Branch saveBranch = saveOrUpdate(branch);
		return branchTransformer.transform(saveBranch);
	}

	@Override
	public List<BranchDto> getbranchBySBUid(Integer sbuId) {
		log.info("BranchDaoImpl.getbranchBySBUid(String username) invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
		criteria.createAlias("branch.sbuId", "sbu");
		criteria.add(Restrictions.eq("sbu.id", sbuId));
		
		List<BranchDto> branchDtoList = null;
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (branchList != null && !branchList.isEmpty()) {
			branchDtoList = new ArrayList<>();
			for (Branch branch : branchList) {
				branchDtoList.add(branchTransformer.transform(branch));
			}
		}
		return branchDtoList;
	}

}
