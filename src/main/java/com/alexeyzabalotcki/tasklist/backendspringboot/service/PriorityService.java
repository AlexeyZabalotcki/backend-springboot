package com.alexeyzabalotcki.tasklist.backendspringboot.service;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Priority;
import com.alexeyzabalotcki.tasklist.backendspringboot.repository.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    public Priority add(Priority priority) {
        return priorityRepository.save(priority);
    }

    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }

    public Priority update(Priority priority) {
        return priorityRepository.save(priority);
    }

    public List<Priority> findByTitle(String title) {
        return priorityRepository.findByTitle(title);
    }

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }
}
