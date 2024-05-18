package com.example.enrollment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassDTO {
    private Long classId;
    private int totalEnrollments;
    private int totalCredits;
    private double totalTuition;
}
