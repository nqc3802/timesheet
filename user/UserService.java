package com.example.timesheet.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.timesheet.role.Role;
import com.example.timesheet.role.RoleRepository;
import com.example.timesheet.user_role.UserRole;
import com.example.timesheet.user_role.UserRoleRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository r;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository r, PasswordEncoder passwordEncoder) {
        this.r = r;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return r.findAll();
    }

    public User getUser(int id) {
        return r.findById(id);
    }

    public User editUser(User user, int id) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        currentUser.setName(user.getName());
        currentUser.setDob(user.getDob());
        currentUser.setGender(user.getGender());
        currentUser.setEmail(user.getEmail());
        currentUser.setAddress(user.getAddress());
        currentUser.setUser_type(user.getUser_type());
        currentUser.setLevel(user.getLevel());
        currentUser.setPosition_id(user.getPosition_id());
        currentUser.setBranches_id(user.getBranches_id());
        currentUser.setTrainner_id(user.getTrainner_id());
        currentUser.setSalary(user.getSalary());
        currentUser.setState(user.getState());
        currentUser.setMorning_start_time(user.getMorning_start_time());
        currentUser.setMorning_end_time(user.getMorning_end_time());
        currentUser.setAfternoon_start_time(user.getAfternoon_start_time());
        currentUser.setAfternoon_end_time(user.getAfternoon_end_time());
        return r.save(currentUser);
    }

    public User resetPassword(User user, int id) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return r.save(currentUser);
    }

    public User deactivateUser(int id, User user) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        currentUser.setState("Inactive");
        return r.save(currentUser);
    }

    public User activateUser(int id, User user) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        currentUser.setState("Active");
        return r.save(currentUser);
    }

    public User editUserRole(int id, List<Integer> role_ids) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        List<UserRole> existingUserRoles = userRoleRepository.findByUser(currentUser);
        List<Role> newRole = role_ids.stream()
                .map(role_id -> roleRepository.findById(role_id)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role_id)))
                .collect(Collectors.toList());
        existingUserRoles.stream()
                .filter(userRole -> !newRole.contains(userRole.getRole()))
                .forEach(userRoleRepository::delete);

        newRole.stream()
                .filter(role -> existingUserRoles.stream().noneMatch(userRole -> userRole.getRole().equals(role)))
                .forEach(role -> {
                    UserRole userRole = new UserRole();
                    userRole.setUser(currentUser);
                    userRole.setRole(role);
                    userRoleRepository.save(userRole);
                });
        return r.findById(id);
    }

    public User editWorkingTime(User user, int id) {
        User currentUser = r.findById(id);
        if (currentUser == null) {
            User messageUser = new User();
            messageUser.setUsername("User not found");
            return messageUser;
        }
        currentUser.setMorning_start_time(user.getMorning_start_time());
        currentUser.setMorning_end_time(user.getMorning_end_time());
        currentUser.setAfternoon_start_time(user.getAfternoon_start_time());
        currentUser.setAfternoon_end_time(user.getAfternoon_end_time());
        return r.save(currentUser);
    }
}
