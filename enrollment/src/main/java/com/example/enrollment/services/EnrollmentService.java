package com.example.enrollment.services;

import com.example.enrollment.DTO.EnrollmentDTO;
import com.example.enrollment.models.Classzz;
import com.example.enrollment.models.Enrollment;
import com.example.enrollment.repositories.EnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private  final EnrollmentRepository repository;
    private final ClassService classService;

    public  void saveEnrollment(EnrollmentDTO enrollmentDTO){
        Classzz classzz = classService.findById(enrollmentDTO.getClassId()).orElseThrow();
        Enrollment enrollment = Enrollment.builder()
                .enrollmentId(enrollmentDTO.getEnrollmentId())
                .studentId(enrollmentDTO.getStudentId())
                .classId(classzz)
                .registrationDate(enrollmentDTO.getRegistrationDate())
                .status(enrollmentDTO.getStatus())
                .confirmedDate(enrollmentDTO.getConfirmedDate())
                .creditEarned(enrollmentDTO.getCreditEarned())
                .tuitionFee(enrollmentDTO.getTuitionFee())
                .notes(enrollmentDTO.getNotes())
                        .build();
        repository.save(enrollment);
    }
    public List<Enrollment> findALlEnrollment(){
        return repository.findAll();
    }
    public Optional<Enrollment> findById(long id){
        return repository.findById(id);
    }

//    public Enrollment updateEnrollment(long id, Enrollment updateErm){
//
//        Enrollment enrollment = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));
//        Classzz classzz = classService.findById(enrollment.getClassId().getClassId()).orElse(null);
//        if(enrollment != null){
//            enrollment.setClassId(classzz);
//            enrollment.setRegistrationDate(updateErm.getRegistrationDate());
//            enrollment.setConfirmedDate(updateErm.getConfirmedDate());
//            enrollment.setStatus(updateErm.getStatus());
//            enrollment.setNotes(updateErm.getNotes());
//            return repository.save(enrollment);
//        }else {
//            throw  new EntityNotFoundException("not found");
//        }
//    }
public Enrollment updateEnrollment(long id, Enrollment updateErm) {
    // Tìm kiếm enrollment cần cập nhật
    Optional<Enrollment> optionalEnrollment = repository.findById(id);
    if (optionalEnrollment.isPresent()) {
        Enrollment enrollment = optionalEnrollment.get();

        // Cập nhật thông tin từ updateErm
        enrollment.setRegistrationDate(updateErm.getRegistrationDate());
        enrollment.setConfirmedDate(updateErm.getConfirmedDate());
        enrollment.setStatus(updateErm.getStatus());
        enrollment.setCreditEarned(updateErm.getCreditEarned());
        enrollment.setTuitionFee(updateErm.getTuitionFee());
        enrollment.setNotes(updateErm.getNotes());

        // Lưu cập nhật vào cơ sở dữ liệu và trả về enrollment đã được cập nhật
        return repository.save(enrollment);
    } else {
        // Nếu không tìm thấy enrollment, ném ra ngoại lệ EntityNotFoundException
        throw new EntityNotFoundException("Enrollment with id " + id + " not found");
    }
}

    public void deleteErm(long id){
        repository.deleteById(id);
    }
}
