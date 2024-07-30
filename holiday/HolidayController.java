package com.example.timesheet.holiday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("api/v1/holiday")
@PreAuthorize("hasRole('ROLE_Admin')")
@RestController
public class HolidayController {
    @Autowired
    private HolidayService holidayService;

    @GetMapping
    public List<Holiday> getHolidays() {
        return holidayService.getHolidays();
    }

    @GetMapping("/{year}/{month}")
    public List<Holiday> getHolidaysByMonthAndYear(@PathVariable int month, @PathVariable int year) {
        return holidayService.getHolidaysByMonthAndYear(month, year);
    }

    @PostMapping
    public Holiday addNewHoliday(@RequestBody Holiday holiday) {
        return holidayService.addNewHoliday(holiday);
    }
}
