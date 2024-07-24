package com.example.timesheet.leave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @GetMapping("/leave")
    public List<Leave> getLeave() {
        return leaveService.getLeave();
    }
}
