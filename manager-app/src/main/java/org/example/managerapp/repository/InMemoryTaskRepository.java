package org.example.managerapp.repository;

import org.example.managerapp.entity.Status;
import org.example.managerapp.entity.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
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
                                Status.NEW,
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
                .filter(task -> Objects.equals(task.id(), id))
                .findFirst();
    }

}