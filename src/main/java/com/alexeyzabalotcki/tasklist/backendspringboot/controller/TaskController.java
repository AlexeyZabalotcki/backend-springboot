package com.alexeyzabalotcki.tasklist.backendspringboot.controller;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Task;
import com.alexeyzabalotcki.tasklist.backendspringboot.search.TaskSearchValues;
import com.alexeyzabalotcki.tasklist.backendspringboot.service.TaskService;
import com.alexeyzabalotcki.tasklist.backendspringboot.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {

        MyLogger.displayMethod("TaskController: findAll()-------------------------------------------------");

        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {

        MyLogger.displayMethod("TaskController: add()-------------------------------------------------");

        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        MyLogger.displayMethod("TaskController: update()-------------------------------------------------");

        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("Error: id MUST be fill", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: you MUST fill title form", HttpStatus.NOT_ACCEPTABLE);
        }

        taskService.update(task);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteById(@PathVariable Long id) {

        MyLogger.displayMethod("TaskController: deleteById()-------------------------------------------------");

        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity("That id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        MyLogger.displayMethod("TaskController: deleteById()-------------------------------------------------");

        Task task = null;

        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues values) {

        MyLogger.displayMethod("CategoryController: search()-------------------------------------------------");

        Long categoryId = values.getCategoryId() != null ? values.getCategoryId() : null;
        Long priorityId = values.getPriorityId() != null ? values.getPriorityId() : null;
        Integer completed = values.getCompleted() != null ? values.getCompleted() : null;
        String title = values.getTitle() != null ? values.getTitle() : null;


        String sortColumn = values.getSortColumn() != null ? values.getSortColumn() : null;
        String sortDirection = values.getSortDirection() != null ? values.getSortDirection() : null;

        Integer pageNumber = values.getPageNumber() != null ? values.getPageNumber() : null;
        Integer pageSize = values.getPageSize() != null ? values.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 ||
                sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page result = taskService.findByParams(title, completed, categoryId, priorityId, pageRequest);

        return ResponseEntity.ok(result);
    }
}
