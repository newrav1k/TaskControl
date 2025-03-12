package org.example.managerapp.repository;

import org.example.managerapp.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(Integer id);

    void deleteById(Integer id);

    Task save(Task task);

}