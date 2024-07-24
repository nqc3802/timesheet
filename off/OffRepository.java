package com.example.timesheet.off;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OffRepository extends JpaRepository<Off, Integer> {
    @Query("SELECT o FROM Off o WHERE o.user_id = :user_id AND MONTH(o.date) = :month AND YEAR(o.date) = :year")
    List<Off> findOffsByMonthAndYear(int user_id, int month, int year);

    @Query(value = "SELECT o.* FROM Off o JOIN user_prj up ON o.user_id = up.user_id WHERE up.prj_id = :prj_id", nativeQuery = true)
    List<Off> findOffsByMyProject(int prj_id);

}
