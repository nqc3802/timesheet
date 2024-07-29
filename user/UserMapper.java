package com.example.timesheet.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    List<UserDTO> usersToUserDTOs(List<User> users);
    List<User> userDTOsToUsers(List<UserDTO> userDTOs);
    UserDetailDTO userToUserDetailDTO(User user);
    User userDetailDTOToUser(UserDetailDTO userDetailDTO);
    UserResetpasswordDTO userToUserResetpasswordDTO(User user);
    User userResetpasswordDTOToUser(UserResetpasswordDTO userResetpasswordDTO);
    UserDeactivateDTO userToUserDeactivateDTO(User user);
    User userDeactivateDTOToUser(UserDeactivateDTO userDeactivateDTO);
    UserWorkingtimeDTO userToUserWorkingtimeDTO(User user);
    User userWorkingtimeDTOToUser(UserWorkingtimeDTO userWorkingtimeDTO);
}
