package org.example.managerapp.entity;

import java.time.LocalDateTime;

public record Task(Integer id, String title, String description, Status status, LocalDateTime deadline) {

}