package com.example.timesheet.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String username;
    private String gender;
    private String email;
    private String user_type;
    private String level;
    private String state;
}
