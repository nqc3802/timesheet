package com.example.timesheet.user;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWorkingtimeDTO {
    private int id;
    private String name;
    private Time morning_start_time;
    private Time morning_end_time;
    private Time afternoon_start_time;
    private Time afternoon_end_time;
}
