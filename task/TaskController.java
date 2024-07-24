package com.example.timesheet.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("api/v1/task")
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public Task addNewTask(@RequestBody Task task) {
        return taskService.addNewTask(task);
    }

    @PutMapping("/{id}")
    public Task editTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.editTask(task, id);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }

}
