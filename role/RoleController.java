package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    private RoleService s;

    @GetMapping("/roles")
    public List<Role> getRoles() throws IOException {
        return s.getRoles();
    }

    @PostMapping("/role/add")
    public Role addNewRole(@RequestBody Role role) {
        return s.addNewRole(role);
    }

}
