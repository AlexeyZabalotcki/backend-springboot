package com.alexeyzabalotcki.tasklist.backendspringboot.repository;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t from Task t where " +
            "(:title is null or :title='' or lower(t.title) like lower(concat('%',:title, '%'))) and" +
            "(:completed is null or t.completed=:completed) and " +
            "(:priorityId is null or t.priority.id=:priorityId) and" +
            "(:categoryId is null or t.category.id=:categoryId)"
    )
    List<Task> findByParams(@Param("title") String title, @Param("completed") Integer completed,
                            @Param("categoryId") Long categoryId, @Param("priorityId") Long priorityId);
}
