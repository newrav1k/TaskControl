package org.example.managerapp.payload;

import org.example.managerapp.entity.TaskStatus;

import java.time.LocalDateTime;

public record NewTaskPayload(
        String title,
        String description,
        TaskStatus status,
        LocalDateTime deadline) {

}