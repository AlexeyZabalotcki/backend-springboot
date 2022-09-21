package com.alexeyzabalotcki.tasklist.backendspringboot.repository;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
}
