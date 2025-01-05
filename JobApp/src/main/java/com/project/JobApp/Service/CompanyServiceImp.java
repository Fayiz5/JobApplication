package com.project.JobApp.Service;

import com.project.JobApp.Entity.Company;
import com.project.JobApp.Repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CompanyServiceImp implements CompanyService {

    CompanyRepository companyRepository;

    @Autowired
    CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        log.info("Fetching all jobs");
        List<Company> companies = companyRepository.findAll();
        log.info("Retrieved {} companies from the database", companies.size());
        return companies;
    }

    @Override
    public Company CreateCompany(Company job) {
        log.info("Creating a new company: {}", job);
        Company savedCompany = companyRepository.save(job);
        log.info("Company created successfully with ID: {}", savedCompany.getId());
        return savedCompany;
    }

    public Company getCompanyById(Long id) {
        log.info("Fetching company with ID: {}", id);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company with ID " + id + " not found"));
        log.info("Company fetched successfully: {}", company);
        return company;

    }

    @Override
    public boolean deleteCompanyById(Long id) {
        log.info("Attempting to delete company with ID: {}", id);
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            log.info("Company with ID: {} deleted successfully", id);
            return true;
        } else {
            log.warn("Company with ID: {} not found. Delete operation failed.", id);
            return false;
        }

    }

    @Override
    public Company updateCompany(Company company, Long id) {
        log.info("Attempting to update company with ID: {}", id);
        Company updatedCompany = companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(company.getName());
                    existingCompany.setDescription(company.getDescription());
                    existingCompany.setJobs(company.getJobs());
                    // Add other fields as needed
                    log.info("Updating company with new details: {}", existingCompany);
                    return companyRepository.save(existingCompany);
                })
                .orElseThrow(() -> new RuntimeException("Company with ID " + id + " not found"));
        log.info("Company with ID: {} updated successfully", id);
        return updatedCompany;
    }
}



