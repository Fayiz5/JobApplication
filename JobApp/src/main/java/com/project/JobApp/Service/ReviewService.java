package com.project.JobApp.Service;

import com.project.JobApp.Entity.Job;
import com.project.JobApp.Entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAll();
    Review CreateReview(Long id,Review review);

    Review getReviewById(Long id);

    Review updateReview(Review review, Long id);

    List<Review> getAllReviews(Long id);

    boolean deleteReview(Long companyid, Long reviewId);
}
