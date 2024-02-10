package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.Branch;
import com.pos_main.Dto.BranchDto;

/**
 * Title: BranchTransformer.java. Company: www.softmacs.com Copyright: Copyright
 * (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Component
public class BranchTransformer implements BaseTransformer<Branch, BranchDto> {
	
	@Override
	public BranchDto transform(Branch branch) {
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = new BranchDto();
			branchDto.setId(branch.getId());
			branchDto.setBranchName(branch.getBranchName());
			branchDto.setAddress(branch.getAddress());
			branchDto.setContactNumber(branch.getContactNumber());
			branchDto.setEmailAddress(branch.getEmailAddress());
			branchDto.setBranchCode(branch.getBranchCode());
		}
		return branchDto;
	}

	@Override
	public Branch reverseTransform(BranchDto branchDto) {
		Branch branch = null;
		if (branchDto != null) {
			branch = new Branch();
			branch.setId(branchDto.getId());
			branch.setBranchName(branchDto.getBranchName());
			branch.setAddress(branchDto.getAddress());
			branch.setContactNumber(branchDto.getContactNumber());
			branch.setEmailAddress(branchDto.getEmailAddress());
			branch.setBranchCode(branchDto.getBranchCode());
		}
		return branch;
	}

}
