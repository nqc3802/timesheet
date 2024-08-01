package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("api/v1/role")
@PreAuthorize("hasRole('ROLE_Admin')")
@RestController
public class RoleController {
    @Autowired
    private RoleService s;

    @GetMapping
    public List<RoleDTO> getRoles() {
        return s.getRoles();
    }

    @PostMapping
    public RoleDTO addNewRole(@RequestBody RoleDTO roleDTO) {
        return s.addNewRole(roleDTO);
    }

}
