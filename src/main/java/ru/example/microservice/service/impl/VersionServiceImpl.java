package ru.example.microservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.microservice.entity.Version;
import ru.example.microservice.repository.VersionRepository;
import ru.example.microservice.service.VersionService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;

    @Override
    @Transactional(readOnly = true)
    public Version getCurrentVersion() {
        return versionRepository.getCurrentVersion()
                .orElseThrow(() -> new EntityNotFoundException("Версий не найдено"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Version add(Version version) {
        return versionRepository.save(version);
    }

}
