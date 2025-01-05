package com.project.JobApp.Repository;

import com.project.JobApp.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByComapnyId(Long id);
}
