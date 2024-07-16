package com.example.timesheet.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Task editTask(Task task, int id) {
        Task currentTask = taskRepository.findById(id);
        if (currentTask == null) {
            Task messageTask = new Task();
            messageTask.setName("Task not found");
            return messageTask;
        }
        currentTask.setName(task.getName());
        currentTask.setBillable_hours(task.getBillable_hours());
        currentTask.setHours(task.getHours());
        return taskRepository.save(currentTask);
    }

    public Task deleteTask(int id) {
        return taskRepository.deleteById(id);
    }

}
