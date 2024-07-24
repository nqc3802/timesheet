package com.example.timesheet.user_role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.timesheet.user.User;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUserId(int user_id);
    List<UserRole> findByRoleId(int role_id);
    List<UserRole> findByUser(User user);
}
