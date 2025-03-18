package org.example.catalogueservice.payload;

import org.example.catalogueservice.entity.TaskStatus;

import java.time.LocalDateTime;

public record NewTaskPayload(String title, String description,
                             TaskStatus status, LocalDateTime deadline) {

}