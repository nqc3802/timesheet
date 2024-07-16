package com.example.timesheet.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository r;
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
        currentUser.setDob(currentUser.getDob());
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
        currentUser.setRole(user.getRole());
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
}
