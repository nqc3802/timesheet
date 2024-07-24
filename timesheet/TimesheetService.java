package com.example.timesheet.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
    @Autowired
    private TimesheetRepository timesheetRepository;

    public Timesheet addNewTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public void submitTimesheet(LocalDate date, int user_id) {
        List<Timesheet> newTimesheets = timesheetRepository.findByStatusAndDate("New", date, user_id);
        for (Timesheet timesheet : newTimesheets) {
            timesheet.setStatus("Pending");
            timesheetRepository.save(timesheet);
        }
    }

    public List<Timesheet> getTimesheetsByMonthAndYear(int user_id, int month, int year) {
        return timesheetRepository.findTimesheetsByMonthAndYear(user_id, month, year);
    }
}
