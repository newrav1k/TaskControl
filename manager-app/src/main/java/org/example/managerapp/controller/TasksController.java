package org.example.managerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.managerapp.client.TaskClient;
import org.example.managerapp.entity.Newrav1kUser;
import org.example.managerapp.entity.Task;
import org.example.managerapp.payload.NewTaskPayload;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskClient taskClient;

    @ModelAttribute("userId")
    public Long getUserId(@AuthenticationPrincipal Newrav1kUser user) {
        return user.getId();
    }

    @GetMapping("/list")
    public String tasks(Model model, @ModelAttribute("userId") Long userId) {
        model.addAttribute("tasks", this.taskClient.findAllByUserId(userId));
        return "/tasks/list";
    }

    @GetMapping("/list/all")
    @PreAuthorize("hasRole('ADMIN')")
    public String tasks(Model model) {
        model.addAttribute("tasks", this.taskClient.findAll());
        return "/tasks/list";
    }

    @GetMapping("/create")
    public String showCreateTask(Model model) {
        model.addAttribute("task", new NewTaskPayload());
        return "/tasks/create-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("userId") Long userId,
                             @Valid NewTaskPayload payload,
                             BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Task task = this.taskClient.createTask(payload.getTitle(), payload.getDescription(),
                    payload.getStatus(), payload.getDeadline(), userId);
            return "redirect:/tasks/list";
        }
    }

}