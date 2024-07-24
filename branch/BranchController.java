package com.example.timesheet.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin
@RequestMapping("api/v1/branch")
@RestController
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<Branch> getBranches() {
        return branchService.getBranches();
    }

    @PostMapping
    public Branch addNewBranch(@RequestBody Branch branch) {
        return branchService.addNewBranch(branch);
    }

    @PutMapping("/{id}")
    public Branch editBranch(@PathVariable int id, @RequestBody Branch branch) {
        return branchService.editBranch(branch, id);
    }

    @GetMapping("/search/{keyword}")
    public List<Branch> search(@PathVariable String keyword) {
        return branchService.search(keyword);
    }

    @DeleteMapping("/{id}")
    public Branch deleteBranch(@PathVariable int id) {
        return branchService.deleteBranch(id);
    }
    
}
