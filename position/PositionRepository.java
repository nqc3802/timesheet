package com.example.timesheet.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Position findById(int id);

    List<Position> findByNameContainingOrShortnameContainingOrCodeContaining(String name, String shortname,
            String code);

    Position deleteById(int id);
}
