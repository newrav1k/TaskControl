package org.example.managerapp.entity;

import lombok.Getter;

@Getter
public enum TaskStatus {
    NEW("Новая"),
    IN_PROGRESS("В процессе"),
    COMPLETED("Завершена");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }

}