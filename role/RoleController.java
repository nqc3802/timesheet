package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("api/v1/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService s;

    @GetMapping
    public List<Role> getRoles() throws IOException {
        return s.getRoles();
    }

    @PostMapping
    public Role addNewRole(@RequestBody Role role) {
        return s.addNewRole(role);
    }

}