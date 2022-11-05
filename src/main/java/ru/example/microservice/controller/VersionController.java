package ru.example.microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.microservice.entity.Version;
import ru.example.microservice.service.VersionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/version")
public class VersionController {

    private final VersionService versionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "version")
    public ResponseEntity<Version> get() {
        return ResponseEntity.ok(versionService.getCurrentVersion());
    }

}
