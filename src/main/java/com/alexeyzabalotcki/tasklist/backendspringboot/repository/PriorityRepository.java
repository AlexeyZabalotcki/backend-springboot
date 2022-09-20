package com.alexeyzabalotcki.tasklist.backendspringboot.repository;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
