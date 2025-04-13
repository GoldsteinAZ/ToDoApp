package com.example.ToDoApp.controller;

import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.model.User;
import com.example.ToDoApp.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskUIController {

    private final TaskService taskService;

    public TaskUIController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(@AuthenticationPrincipal User user, Model model) {
        List<Task> tasks = taskService.getTasksForUser(user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task()); // For the form
        return "tasks"; // Returns tasks.html
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("newTask") Task task, @AuthenticationPrincipal User user) {
        taskService.addTask(task, user);
        return "redirect:/tasks"; // Refresh the page
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId, @AuthenticationPrincipal User user) {
        taskService.deleteTask(taskId, user);
        return "redirect:/tasks";
    }
}