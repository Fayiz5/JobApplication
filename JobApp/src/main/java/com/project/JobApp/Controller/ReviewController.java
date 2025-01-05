package com.project.JobApp.Controller;

import com.project.JobApp.Entity.Company;
import com.project.JobApp.Entity.Review;
import com.project.JobApp.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("Companies/{companyId}")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long CompanyId, Long ReviewId) {
        //return ResponseEntity.ok(reviewService.getReviewById(id));
        List<Review> reviews = reviewService.getAllReviews(CompanyId);
        return ResponseEntity.ok(reviews.stream().filter(review -> review.getId().equals(ReviewId)).findFirst().orElse(null));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@PathVariable Long CompanyId, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.CreateReview(CompanyId,review));
    }

//    @PutMapping("/reviews/{id}")
//    public ResponseEntity<Review> updateReview(@PathVariable Long Companyid,@PathVariable Long ReviewId, @RequestBody Review review) {
//        return ResponseEntity.ok(reviewService.updateReview(id, review));
//    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long Companyid, @PathVariable Long ReviewId) {
        reviewService.deleteReview(Companyid,ReviewId);
        return ResponseEntity.noContent().build();
    }
    
}
