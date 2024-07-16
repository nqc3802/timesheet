package com.example.timesheet.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService s;

    @GetMapping("/users")
    public List<User> getUsers() throws IOException {
        return s.getUsers();
    }
    
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return s.getUser(id);
    }

    @PutMapping("/user/{id}")
    public User editUser(@PathVariable int id, @RequestBody User user) {
        return s.editUser(user, id);
    }

    @PutMapping("/resetpassword/{id}")
    public User resetPassword(@PathVariable int id, @RequestBody User user) {
        return s.resetPassword(user, id);
    }

    @PutMapping("/user/deactivate/{id}")
    public User deactivateUser(@PathVariable int id, @RequestBody User user) {
        return s.deactivateUser(id, user);
    }

    @PutMapping("/user/activate/{id}")
    public User activateUser(@PathVariable int id, @RequestBody User user) {
        return s.activateUser(id, user);
    }
}
