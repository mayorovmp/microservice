package ru.example.microservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.example.microservice.entity.Version;

import java.util.Optional;

public interface VersionRepository  extends PagingAndSortingRepository<Version, Long> {

    @Query("select v from Version v " +
            "where v.updated = (select max(v2.updated) from Version v2)")
    Optional<Version> getCurrentVersion();

}
