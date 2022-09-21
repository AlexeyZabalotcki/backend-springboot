package com.alexeyzabalotcki.tasklist.backendspringboot.controller;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Stat;
import com.alexeyzabalotcki.tasklist.backendspringboot.repository.StatRepository;
import com.alexeyzabalotcki.tasklist.backendspringboot.util.MyLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    private final StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    private final Long defaultId = 1l;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        MyLogger.displayMethod("PriorityController: findById()-------------------------------------------------");

        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
