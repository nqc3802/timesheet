package com.example.timesheet.off;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/v1/off")
@PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_BasicUser')")
@RestController
public class OffController {
    @Autowired
    private OffService offService;

    @GetMapping
    public List<Off> getOffs() {
        return offService.getOffs();
    }

    @PostMapping
    public ResponseEntity<?> addNewOff(@RequestBody Off off) {
        try {
            Off offRequest = offService.addNewOff(off);
            return ResponseEntity.ok(offRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public List<Off> getOffsByMonthAndYear(@PathVariable int user_id, @RequestParam int month, @RequestParam int year) {
        return offService.getOffsByMonthAndYear(user_id, month, year);
    }

    @GetMapping("/project/{prj_id}")
    public List<Off> getOffsByMyProject(@PathVariable int prj_id) {
        return offService.getOffsByMyProject(prj_id);
    }
}
