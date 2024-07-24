package com.example.timesheet.off;

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
@Table(name = "off")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Off {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private String request_type;
    private Date date;
    private Float hour;
    private Integer leave_type_id;
    private String reason;
    @Builder.Default
    private String status = "Pending";
}
