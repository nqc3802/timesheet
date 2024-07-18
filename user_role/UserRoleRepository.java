package com.example.timesheet.user_role;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.timesheet.user.User;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByUserId(int user_id);
    UserRole findByRoleId(int role_id);
    Set<UserRole> findByUser(User user);
}
