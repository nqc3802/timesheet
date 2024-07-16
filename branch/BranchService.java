package com.example.timesheet.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    public Branch addNewBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch editBranch(Branch branch, int id) {
        Branch currentBranch = branchRepository.findById(id);
        if (currentBranch == null) {
            Branch messageBranch = new Branch();
            messageBranch.setName("Branch not found");
            return messageBranch;
        }
        currentBranch.setName(branch.getName());
        currentBranch.setCode(branch.getCode());
        currentBranch.setAddress(branch.getAddress());
        return branchRepository.save(currentBranch);
    }

    public List<Branch> search(String keyword) {
        return branchRepository.findByNameContainingOrAddressContaining(keyword, keyword);
    }

    public Branch deleteBranch(int id) {
        return branchRepository.deleteById(id);
    }

}
