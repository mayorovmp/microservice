package ru.example.microservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.example.microservice.entity.Version;
import ru.example.microservice.service.VersionService;

import java.sql.Timestamp;

@Slf4j
@Component
@RequiredArgsConstructor
public class VersionUpJob {

    private final VersionService versionService;

    @Scheduled(cron = "${jobs.version-up.cron}")
    public void versionUp() {
        var version = Version.builder()
                .updated(new Timestamp(System.currentTimeMillis()))
                .build();
        versionService.add(version);
        log.info(version.toString());
    }

}
