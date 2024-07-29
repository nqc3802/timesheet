package com.example.timesheet.user;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {
    private int id;
    private String name;
    private String username;
    private Date dob;
    private String gender;
    private String email;
    private String address;
    private String user_type;
    private String level;
    private int position_id;
    private int branches_id;
    private int trainner_id;
    private int salary;
    private String state;
    private Time morning_start_time;
    private Time afternoon_start_time;
    private Time morning_end_time;
    private Time afternoon_end_time;
}
