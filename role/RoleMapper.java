package com.example.timesheet.role;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);
    List<Role> roleDTOsToRoles(List<RoleDTO> roleDTOs);
}
