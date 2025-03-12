package org.example.managerapp.service;

import org.example.managerapp.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findTaskById(int taskId);

}