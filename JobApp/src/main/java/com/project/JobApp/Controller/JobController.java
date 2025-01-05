package com.project.JobApp.Controller;

import com.project.JobApp.Entity.Job;
import com.project.JobApp.Service.JobServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs") // Standardized REST resource naming
public class JobController {

    private final JobServiceImp jobServiceImp;

    @Autowired
    public JobController(JobServiceImp jobServiceImp) {
        this.jobServiceImp = jobServiceImp;
    }

    // Create a new job
    @PostMapping("/")
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
        Job createdJob = jobServiceImp.CreateJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED); // Return 201 Created
    }

    // Retrieve all jobs
    @GetMapping("/")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobServiceImp.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK); // Return 200 OK
    }

    // Retrieve a job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobServiceImp.getJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK); // Return 200 OK
    }

    // Delete a job by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean isDeleted = jobServiceImp.deleteJobById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Job with ID " + id + " deleted successfully.", HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("Job with ID " + id + " not found.", HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Update a job by ID
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateById(@Valid @RequestBody Job job, @PathVariable Long id) {
        jobServiceImp.updateJob(job, id);
        Job updatedJob = jobServiceImp.getJobById(id);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK); // Return 200 OK
    }
}
