package com.project.JobApp.Service;

import com.project.JobApp.Entity.Job;
import com.project.JobApp.Repository.JobRepository;
import com.project.JobApp.Exception.JobNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImp implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImp.class);

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        logger.info("Fetching all jobs");
        List<Job> jobs = jobRepository.findAll();
        logger.info("Retrieved {} jobs from the database", jobs.size());
        return jobs;
    }

    @Override
    public Job CreateJob(Job job) {
        logger.info("Creating a new job: {}", job.getTitle());
        Job createdJob = jobRepository.save(job);
        logger.info("Job created with ID: {}", createdJob.getId());
        return createdJob;
    }

    @Override
    public Job getJobById(Long id) {
        logger.info("Fetching job with ID: {}", id);
        return jobRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Job with ID {} not found", id);
                    return new JobNotFoundException("Job not found with ID: " + id);
                });
    }

    @Override
    public boolean deleteJobById(Long id) {
        logger.info("Attempting to delete job with ID: {}", id);
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            logger.info("Job with ID {} deleted successfully", id);
            return true;
        } else {
            logger.warn("Job with ID {} not found for deletion", id);
            return false;
        }
    }

    @Override
    public Job updateJob(Job job, Long id) {
        logger.info("Updating job with ID: {}", id);
        return jobRepository.findById(id)
                .map(existingJob -> {
                    existingJob.setTitle(job.getTitle());
                    existingJob.setDescription(job.getDescription());
                    existingJob.setMinSalary(job.getMinSalary());
                    existingJob.setMaxSalary(job.getMaxSalary());
                    Job updatedJob = jobRepository.save(existingJob);
                    logger.info("Job with ID {} updated successfully", id);
                    return updatedJob;
                })
                .orElseThrow(() -> {
                    logger.error("Job with ID {} not found for update", id);
                    return new JobNotFoundException("Job not found with ID: " + id);
                });
    }
}
