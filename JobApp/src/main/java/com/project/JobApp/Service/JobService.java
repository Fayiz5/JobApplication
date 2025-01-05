package com.project.JobApp.Service;

import com.project.JobApp.Entity.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    Job CreateJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    Job updateJob(Job job, Long id);

}
