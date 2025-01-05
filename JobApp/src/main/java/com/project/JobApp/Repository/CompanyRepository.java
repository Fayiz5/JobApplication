package com.project.JobApp.Repository;

import com.project.JobApp.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
