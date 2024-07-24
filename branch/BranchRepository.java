package com.example.timesheet.branch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Branch findById(int id);
    List<Branch> findByNameContainingOrAddressContaining(String name, String address);
    Branch deleteById(int id);
}
