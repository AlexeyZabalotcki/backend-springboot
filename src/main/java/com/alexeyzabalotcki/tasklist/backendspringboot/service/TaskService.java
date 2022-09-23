package com.alexeyzabalotcki.tasklist.backendspringboot.service;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Task;
import com.alexeyzabalotcki.tasklist.backendspringboot.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task add(Task category) {
        return taskRepository.save(category);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Task category) {
        return taskRepository.save(category);
    }

    public Page<Task> findByParams(String title,
                                   Integer completed,
                                   Long priorityId,
                                   Long categoryId,
                                   PageRequest paging) {
        return taskRepository.findByParams(title, completed, categoryId, priorityId, paging);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }
}
