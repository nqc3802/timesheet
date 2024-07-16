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

@CrossOrigin
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/task/add")
    public Task addNewTask(@RequestBody Task task) {
        return taskService.addNewTask(task);
    }

    @PutMapping("/task/{id}")
    public Task editTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.editTask(task, id);
    }

    @DeleteMapping("/task/{id}")
    public Task deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }

}
