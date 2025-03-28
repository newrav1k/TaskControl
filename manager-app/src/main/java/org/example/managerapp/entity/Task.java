package org.example.managerapp.entity;

import java.time.LocalDateTime;

public record Task(Integer id,
                   String title,
                   String description,
                   String status,
                   LocalDateTime deadline) {

}