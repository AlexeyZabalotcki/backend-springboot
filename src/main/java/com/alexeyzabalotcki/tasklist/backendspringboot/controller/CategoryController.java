package com.alexeyzabalotcki.tasklist.backendspringboot.controller;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Category;
import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Priority;
import com.alexeyzabalotcki.tasklist.backendspringboot.search.CategorySearchValues;
import com.alexeyzabalotcki.tasklist.backendspringboot.service.CategoryService;
import com.alexeyzabalotcki.tasklist.backendspringboot.util.MyLogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll() {

        MyLogger.displayMethod("CategoryController: findAll()-------------------------------------------------");
        return categoryService.findAllByOrderTitles();

    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        MyLogger.displayMethod("CategoryController: add()-------------------------------------------------");
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() != null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        MyLogger.displayMethod("CategoryController: update()-------------------------------------------------");

        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("Error: id MUST be fill", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: you MUST fill title form", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        MyLogger.displayMethod("CategoryController: findById()-------------------------------------------------");

        Category category = null;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id) {

        MyLogger.displayMethod("CategoryController: deleteById()-------------------------------------------------");

        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity("That id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues values) {

        MyLogger.displayMethod("CategoryController: search()-------------------------------------------------");

        return ResponseEntity.ok(categoryService.findByTitle(values.getText()));
    }
}
