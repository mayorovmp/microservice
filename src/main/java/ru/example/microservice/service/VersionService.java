package ru.example.microservice.service;

import ru.example.microservice.entity.Version;

public interface VersionService {
    Version getCurrentVersion();
    Version add(Version version);
}
