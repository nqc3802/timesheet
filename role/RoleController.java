package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RequestMapping("api/v1/role")
@PreAuthorize("hasRole('ROLE_Admin')")
@RestController
public class RoleController {
    @Autowired
    private RoleService s;

    @GetMapping
    public Page<RoleDTO> getRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "name") String sort
            ) {
        return s.getRoles(page, size, sort);
    }

    @PostMapping
    public RoleDTO addNewRole(@RequestBody RoleDTO roleDTO) {
        return s.addNewRole(roleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RoleProjection>> getRolesByRoleId(@PathVariable int id) {
        List<RoleProjection> users = s.getUsersByRoleId(id);
        return ResponseEntity.ok(users);
    }

}
