package com.alexeyzabalotcki.tasklist.backendspringboot.repository;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByTitleAsc();

}
