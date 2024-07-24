package com.example.timesheet.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;

    @PostMapping("/timesheet/add")
    public Timesheet addNewTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.addNewTimesheet(timesheet);
    }

    @PostMapping("/timesheet/submit/{user_id}")
    public ResponseEntity<?> submitTimesheet(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable int user_id) {
        timesheetService.submitTimesheet(date, user_id);
        return ResponseEntity.ok("Submit timesheet successfully");
    }

    @GetMapping("/timesheet/{user_id}")
    public List<Timesheet> getTimesheetsByMonthAndYear(@PathVariable int user_id, @RequestParam int month,
            @RequestParam int year) {
        return timesheetService.getTimesheetsByMonthAndYear(user_id, month, year);
    }
}
