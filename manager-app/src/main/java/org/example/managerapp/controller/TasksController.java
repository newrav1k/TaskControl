package org.example.managerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.managerapp.client.TaskClient;
import org.example.managerapp.entity.Task;
import org.example.managerapp.payload.NewTaskPayload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskClient taskClient;

    @GetMapping("/list")
    public String tasks(Model model) {
        model.addAttribute("tasks", this.taskClient.findAll());
        return "/tasks/list";
    }

    @GetMapping("/create")
    public String showCreateTask() {
        return "/tasks/create-task";
    }

    @PostMapping("/create")
    public String createTask(@Valid NewTaskPayload payload,
                             BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Task task = this.taskClient.createTask(payload.title(), payload.description(),
                    payload.status(), payload.deadline());
            return "redirect:/tasks/list";
        }
    }

}