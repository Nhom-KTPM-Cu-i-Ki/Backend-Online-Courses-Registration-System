package com.example.enrollment.DTO;

import com.example.enrollment.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentDTO {
    private long enrollmentId;
    private long studentId;
    private int classId;
    private LocalDate registrationDate;
    private Status status ;
    private LocalDate confirmedDate;
    private int creditEarned;
    private double tuitionFee;
    private String notes;
}
