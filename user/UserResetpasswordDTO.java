package com.example.timesheet.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResetpasswordDTO {
    private int id;
    private String password;
}
