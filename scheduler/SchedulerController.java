package com.example.timesheet.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.timesheet.config.DynamicSchedulerConfig;

@RestController
@RequestMapping("/api/v1/scheduler")
public class SchedulerController {

    @Autowired
    private DynamicSchedulerConfig dynamicSchedulerConfig;

    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/updateCron")
    public ResponseEntity<String> updateCronExpression(@RequestParam String newCronExpression) {
        dynamicSchedulerConfig.updateCronSchedule(newCronExpression);
        return ResponseEntity.ok("Cron expression updated successfully");
    }
}
