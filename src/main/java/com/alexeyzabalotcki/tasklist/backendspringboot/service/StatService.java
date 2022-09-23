package com.alexeyzabalotcki.tasklist.backendspringboot.service;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Stat;
import com.alexeyzabalotcki.tasklist.backendspringboot.repository.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatService {
    private final StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    public Stat findById(Long id) {
        return statRepository.findById(id).get();
    }
}
