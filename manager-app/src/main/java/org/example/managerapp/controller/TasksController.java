package org.example.managerapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.payload.NewTaskPayload;
import org.example.managerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @GetMapping("/list")
    public String tasks(Model model) {
        model.addAttribute("tasks", this.taskService.findAll());
        return "/tasks/list";
    }

    @GetMapping("/create")
    public String showCreateTask(@ModelAttribute Task task, Model model) {
        model.addAttribute("task", task);
        return "/tasks/create-task";
    }

    @PostMapping("/create")
    public String createTask(NewTaskPayload payload) {
        Task task = this.taskService.createTask(payload.title(), payload.description(),
                payload.status(), payload.deadline());
        return "redirect:/tasks/list";
    }

}