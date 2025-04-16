package org.example.managerapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.client.TaskClient;
import org.example.managerapp.entity.Task;
import org.example.managerapp.payload.NewTaskPayload;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/tasks/{taskId:\\d+}")
@RequiredArgsConstructor
public class TaskController {

    private final TaskClient taskClient;

    private final MessageSource messageSource;

    @ModelAttribute("task")
    public Task getTask(@PathVariable("taskId") int taskId) {
        return this.taskClient.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("task.not.found"));
    }

    @GetMapping
    public Task findTask(@ModelAttribute("task") Task task) {
        return task;
    }

    @GetMapping("/delete")
    public String deleteTask() {
        return "/tasks/delete-task";
    }

    @PostMapping("/delete")
    public String deleteTask(@PathVariable("taskId") int taskId) {
        this.taskClient.deleteById(taskId);
        return "redirect:/tasks/list";
    }

    @GetMapping("/edit")
    public String editTask() {
        return "/tasks/edit-task";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute(value = "task", binding = false) Task task,
                           NewTaskPayload payload, Model model) {
        model.addAttribute("task", task);
        this.taskClient.updateTask(task.id(), payload.getTitle(), payload.getDescription(),
                payload.getStatus(), payload.getDeadline());
        return "redirect:/tasks/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model, Locale locale) {
        model.addAttribute("errorCode", HttpStatus.NOT_FOUND.value());
        model.addAttribute("errorMessage",
                this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale));
        return "errors/error";
    }

}