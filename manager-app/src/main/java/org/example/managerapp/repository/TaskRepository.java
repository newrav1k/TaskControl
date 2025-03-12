package org.example.managerapp.repository;

import org.example.managerapp.entity.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

}