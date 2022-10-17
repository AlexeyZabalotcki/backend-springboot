package com.alexeyzabalotcki.tasklist.backendspringboot.controller;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Priority;
import com.alexeyzabalotcki.tasklist.backendspringboot.search.PrioritySearchValues;
import com.alexeyzabalotcki.tasklist.backendspringboot.service.PriorityService;
import com.alexeyzabalotcki.tasklist.backendspringboot.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "http://localhost:4200")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {

        MyLogger.displayMethod("PriorityController: findAll()-------------------------------------------------");

        return priorityService.findAll();

    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        MyLogger.displayMethod("PriorityController: add()-------------------------------------------------");

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() != null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        MyLogger.displayMethod("PriorityController: update()-------------------------------------------------");

        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("Error: id MUST be fill", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: you MUST fill title form", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: you MUST fill color form", HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.update(priority);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        MyLogger.displayMethod("PriorityController: findById()-------------------------------------------------");

        Priority priority = null;

        try {
            priority = priorityService.findById(id);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id) {

        MyLogger.displayMethod("PriorityController: deleteById()-------------------------------------------------");

        try {
            priorityService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity("That id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues values) {

        MyLogger.displayMethod("PriorityController: search()-------------------------------------------------");

        return ResponseEntity.ok(priorityService.findByTitle(values.getText()));
    }
}

