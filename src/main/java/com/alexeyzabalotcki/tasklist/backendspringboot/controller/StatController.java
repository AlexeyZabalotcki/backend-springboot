package com.alexeyzabalotcki.tasklist.backendspringboot.controller;

import com.alexeyzabalotcki.tasklist.backendspringboot.entity.Stat;
import com.alexeyzabalotcki.tasklist.backendspringboot.service.StatService;
import com.alexeyzabalotcki.tasklist.backendspringboot.util.MyLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatController {
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1l;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        MyLogger.displayMethod("PriorityController: findById()-------------------------------------------------");

        return ResponseEntity.ok(statService.findById(defaultId));
    }
}
