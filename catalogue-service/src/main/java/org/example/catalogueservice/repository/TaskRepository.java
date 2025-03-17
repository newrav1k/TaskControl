package org.example.catalogueservice.repository;

import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    void updateTaskByIdAndTitleAndDescriptionAndStatusAndDeadline(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline);

}