package com.project.JobApp.Repository;

import com.project.JobApp.Entity.Job;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JobRepository extends JpaRepository<Job, Id> {
    Optional<Job> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
