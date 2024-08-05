package com.example.timesheet.role;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);
    List<Role> roleDTOsToRoles(List<RoleDTO> roleDTOs);
    default Page<RoleDTO> rolesToRoleDTOsPage(Page<Role> roles) {
        return roles.map(this::roleToRoleDTO);
    }
    default Page<Role> roleDTOsToRoles(Page<RoleDTO> roleDTOs){
        return roleDTOs.map(this::roleDTOToRole);
    }
}
