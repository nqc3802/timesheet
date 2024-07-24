package com.example.timesheet.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
    @Query("SELECT t FROM Timesheet t WHERE t.status = :status AND t.date = :date AND t.user_id = :user_id")
    List<Timesheet> findByStatusAndDate(@Param("status") String status, @Param("date") LocalDate date, int user_id);

    @Query("SELECT t FROM Timesheet t WHERE t.user_id = :user_id AND MONTH(t.date) = :month AND YEAR(t.date) = :year")
    List<Timesheet> findTimesheetsByMonthAndYear(int user_id, int month, int year);
}
