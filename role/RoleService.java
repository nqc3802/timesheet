package com.example.timesheet.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Page<RoleDTO> getRoles(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        // Page<Role> role = roleRepository.findAllAndSort(pageable);
        Page<Role> role = roleRepository.findAll(pageable);
        return RoleMapper.INSTANCE.rolesToRoleDTOsPage(role);
    }

    public RoleDTO addNewRole(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.roleDTOToRole(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.INSTANCE.roleToRoleDTO(savedRole);
    }

	public List<RoleProjection> getUsersByRoleId(int id) {
        return roleRepository.findRoleWithUsers(id);
	}
}
