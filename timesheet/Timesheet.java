package com.example.timesheet.timesheet;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timesheet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int prj_id;
    private int task_id;
    private Date date;
    @Builder.Default
    private String note = "";
    @Builder.Default
    private int working_time = 0;
    @Builder.Default
    private String status = "New";
}
