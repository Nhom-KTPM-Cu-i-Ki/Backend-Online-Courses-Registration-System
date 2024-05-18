package com.example.enrollment.models;

import com.example.enrollment.enums.ClasszzType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classes")
@Builder
public class Classzz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long classId;
    @Column(name = "course_id",nullable = false)
    private long courseId;
    @Column(name = "semester",nullable = false)
    private String semester;
    @Column(name = "max_students",nullable = false)
    private int maxStudents;
    @Column(name = "instructor",nullable = false)
    private String instructor;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClasszzType status;
    @Column(name = "room_id",nullable = false)
    private long roomId;
    @Column(name = "schedule_id",nullable = false)
    private long scheduleId;
}
