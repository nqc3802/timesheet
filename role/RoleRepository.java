package com.example.timesheet.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(int id);
    Optional<Role> findByName(String name);
}
