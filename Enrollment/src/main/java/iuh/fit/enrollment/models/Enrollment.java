package iuh.fit.enrollment.models;

import iuh.fit.enrollment.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollmentId;

    @Column(name = "student_id",nullable = false)
    private long studentId;
    @ManyToOne
    @JoinColumn(name = "class_id",nullable = false)
    private Class classId;
    @Column(name = "registration_date",nullable = false)
    private LocalDate registrationDate;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status ;
    @Column(name = "confirmed_date",nullable = false)
    private LocalDate confirmedDate;
    @Column(name = "credit_earned",nullable = false)
    private int creditEarned;
    @Column(name = "tuition_fee",nullable = false)
    private double tuitionFee;
    @Column(name = "notes")
    private String notes;
}
