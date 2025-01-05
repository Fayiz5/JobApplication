package com.project.JobApp.Controller;


import com.project.JobApp.Entity.Company;
import com.project.JobApp.Service.CompanyServiceImp;
import com.project.JobApp.Service.JobServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company") // Standardized REST resource naming
public class CompanyController {

    private final CompanyServiceImp companyServiceImp;

    @Autowired
    public CompanyController(CompanyServiceImp companyServiceImp) {
        this.companyServiceImp = companyServiceImp;
    }

    // Create a new company
    @PostMapping("/")
    public ResponseEntity<Company> createJob(@Valid @RequestBody Company company) {
        Company createdJob = companyServiceImp.CreateCompany(company);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED); // Return 201 Created
    }

    // Retrieve all jobs
    @GetMapping("/")
    public ResponseEntity<List<Company>> getAllJobs() {
        List<Company> jobs = companyServiceImp.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK); // Return 200 OK
    }

    // Retrieve a company by ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Company company = companyServiceImp.getCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK); // Return 200 OK
    }

    // Delete a company by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean isDeleted = companyServiceImp.deleteCompanyById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Company with ID " + id + " deleted successfully.", HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("Company with ID " + id + " not found.", HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Update a company by ID
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateById(@Valid @RequestBody Company company, @PathVariable Long id) {
        companyServiceImp.updateCompany(company, id);
        Company updatedJob = companyServiceImp.getCompanyById(id);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK); // Return 200 OK
    }
}
