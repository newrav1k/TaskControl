package org.example.catalogueservice.entity;

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