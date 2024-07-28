package com.example.timesheet.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    @Autowired
    private UserService s;

    @GetMapping
    public List<UserDTO> getUsers() throws IOException {
        return s.getUsers();
    }
    
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return s.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDTO editUser(@PathVariable int id, @RequestBody User user) {
        return s.editUser(user, id);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<?> editUserRole(@PathVariable int id, @RequestBody List<Integer> role_id) {
        UserDTO updatedUser = s.editUserRole(id, role_id);
        // if ("User not found".equals(updatedUser.getUsername())) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        // }
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/resetpassword/{id}")
    public UserDTO resetPassword(@PathVariable int id, @RequestBody User user) {
        return s.resetPassword(user, id);
    }

    @PutMapping("/deactivate/{id}")
    public UserDTO deactivateUser(@PathVariable int id, @RequestBody User user) {
        return s.deactivateUser(id, user);
    }

    @PutMapping("/activate/{id}")
    public UserDTO activateUser(@PathVariable int id, @RequestBody User user) {
        return s.activateUser(id, user);
    }

    @PutMapping("/workingtime/{id}")
    public UserDTO editWorkingTime(@PathVariable int id, @RequestBody User user) {
        return s.editWorkingTime(user, id);
    }
}
