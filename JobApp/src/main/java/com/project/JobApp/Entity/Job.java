package com.project.JobApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_app")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Minimum salary must be provided")
    @Min(value = 0, message = "Minimum salary must be non-negative")
    private BigDecimal minSalary;

    @NotNull(message = "Maximum salary must be provided")
    @Min(value = 0, message = "Maximum salary must be non-negative")
    private BigDecimal maxSalary;

    @NotNull(message = "Location must be provided")
    private String location;

    @ManyToOne
    private Company company;
}
