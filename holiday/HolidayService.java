package com.example.timesheet.holiday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
    @Autowired
    private HolidayRepository holidayRepository;

    public List<Holiday> getHolidays() {
        return holidayRepository.findAll();
    }

    public List<Holiday> getHolidaysByMonthAndYear(int month, int year) {
        return holidayRepository.findHolidaysByMonthAndYear(month, year);
    }

    public Holiday addNewHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

}
