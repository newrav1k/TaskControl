package org.example.managerapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/tasks/{taskId:\\d+}")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @ModelAttribute("task")
    public Task getTask(@PathVariable("taskId") int taskId) {
        return this.taskService.findTaskById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
    }

    @GetMapping
    public Task findTask(@ModelAttribute("task") Task task) {
        return task;
    }

    @GetMapping("/delete")
    public String delete() {
        return "/tasks/delete-task";
    }

    @PostMapping("/delete")
    public String deleteTask(@PathVariable("taskId") int taskId) {
        this.taskService.deleteTaskById(taskId);
        return "redirect:/tasks/list";
    }

    @GetMapping("/edit")
    public String editTask() {
        return "/tasks/edit-task";
    }

}