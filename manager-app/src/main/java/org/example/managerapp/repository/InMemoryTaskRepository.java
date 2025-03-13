package org.example.managerapp.repository;

import org.example.managerapp.entity.Task;
import org.example.managerapp.entity.TaskStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = Collections.synchronizedList(new LinkedList<>());

    public InMemoryTaskRepository() {
        IntStream.range(1, 5)
                .forEach(value -> this.tasks.add(
                        new Task(
                                value,
                                "Название: %d".formatted(value),
                                "Описание: %d".formatted(value),
                                TaskStatus.NEW,
                                LocalDateTime.now()
                        )
                ));
    }

    @Override
    public List<Task> findAll() {
        return Collections.unmodifiableList(this.tasks);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return this.tasks.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.tasks.removeIf(task -> Objects.equals(task.getId(), id));
    }

    @Override
    public Task save(Task task) {
        task.setId(this.tasks.stream()
                .max(Comparator.comparingInt(Task::getId))
                .map(Task::getId)
                .orElse(0) + 1);
        this.tasks.add(task);
        return task;
    }

    @Override
    public void updateTaskById(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline) {
        this.tasks.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .ifPresent(task -> {
                    task.setTitle(title);
                    task.setDescription(description);
                    task.setStatus(status);
                    task.setDeadline(deadline);
                });
    }

}