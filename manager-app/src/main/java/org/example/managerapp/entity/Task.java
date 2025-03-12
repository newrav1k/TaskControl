package org.example.managerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Integer id;

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime deadline;

}