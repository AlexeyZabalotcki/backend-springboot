package com.alexeyzabalotcki.tasklist.backendspringboot.service;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Category;
import com.alexeyzabalotcki.tasklist.backendspringboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAllByOrderTitles() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }
}
