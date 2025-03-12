package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

}