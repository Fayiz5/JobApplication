package com.project.JobApp.Service;

import com.project.JobApp.Entity.Company;
import com.project.JobApp.Entity.Review;
import com.project.JobApp.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReviewServiceImp implements ReviewService {
    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImp.class);

    ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Autowired
    ReviewServiceImp(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

   @Override
    public List<Review> findAll() {
        log.info("Fetching all jobs");
        List<Review> reviews = reviewRepository.findAll();
        log.info("Retrieved {} companies from the database", reviews.size());
        return reviews;
    }



    @Override
    public Review CreateReview(Long id, Review review) {
        // Fetch the company by ID
        Company company = companyService.getCompanyById(id);

        if (company != null) {
            // If the company exists, associate it with the review
            review.setCompany(company);
        } else {
            // Handle the case when the company is not found
            log.error("Company with ID {} not found. Cannot create review.", id);
            throw new IllegalArgumentException("Company with ID " + id + " not found.");
        }

        // Log and save the review
        log.info("Creating a new Review: {}", review);
        Review savedReview = reviewRepository.save(review);
        log.info("Review created successfully with ID: {}", savedReview.getId());
        return savedReview;
    }


    @Override
    public Review getReviewById(Long id) {
        log.info("Fetching review with ID: {}", id);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review with ID " + id + " not found"));
        log.info("Review fetched successfully: {}", review);
        return review;
    }



    @Override
    public Review updateReview(Review review, Long id) {
        log.info("Attempting to update review with ID: {}", id);
        Review updatedReview = reviewRepository.findById(id)
                .map(existingReview -> {
                    existingReview.setTitle(review.getTitle());
                    existingReview.setDescription(review.getDescription());
                    existingReview.setRating(review.getRating());
                    existingReview.setCompany(review.getCompany());
                    log.info("Updating review with new details: {}", existingReview);
                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new RuntimeException("Review with ID " + id + " not found"));
        log.info("Review with ID: {} updated successfully", id);
        return updatedReview;
    }

    @Override
    public List<Review> getAllReviews(Long id) {
        List<Review> list= reviewRepository.findByComapnyId(id);
        return list;
    }

    @Override
    public boolean deleteReview(Long companyid, Long reviewId) {
        if(companyService.getCompanyById(companyid) !=null && reviewRepository.existsById(reviewId))
        {
            Review review= reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyid);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else
        {
            return false;
        }
    }
}
