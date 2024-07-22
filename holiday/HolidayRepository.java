package com.example.timesheet.holiday;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    @Query("SELECT h FROM Holiday h WHERE (MONTH(h.start) = :month AND YEAR(h.start) = :year)" +
           " OR (MONTH(h.end) = :month AND YEAR(h.end) = :year)")
    List<Holiday> findHolidaysByMonthAndYear(int month, int year);
}
