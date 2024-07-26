package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getRoles() {
        List<Role> role = roleRepository.findAll();
        return RoleMapper.INSTANCE.rolesToRoleDTOs(role);
    }

    public RoleDTO addNewRole(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.roleDTOToRole(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.INSTANCE.roleToRoleDTO(savedRole);
    }
}
