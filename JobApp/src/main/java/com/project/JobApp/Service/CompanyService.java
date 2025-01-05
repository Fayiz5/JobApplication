package com.project.JobApp.Service;

import com.project.JobApp.Entity.Company;
import com.project.JobApp.Entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();
    Company CreateCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);
    Company updateCompany(Company company, Long id);
}
