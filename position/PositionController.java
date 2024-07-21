package com.example.timesheet.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/positions")
    public List<Position> getPositions() {
        return positionService.getPositions();
    }

    @GetMapping("/position/search/{keyword}")
    public List<Position> search(@PathVariable String keyword) {
        return positionService.search(keyword);
    }

    @PostMapping("/position/add")
    public Position addNewPosition(@RequestBody Position position) {
        return positionService.addNewBranch(position);
    }

    @PutMapping("/position/{id}")
    public Position editPosition(@PathVariable int id, @RequestBody Position position) {
        return positionService.editPosition(id, position);
    }

    @DeleteMapping("/position/{id}")
    public Position deletePosition(@PathVariable int id) {
        return positionService.deletePosition(id);
    }
}
