package com.example.academicInformation.services;

import com.example.academicInformation.models.Grade;
import com.example.academicInformation.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    private GradeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public GradeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveGrade(Grade grade){
        repository.save(grade);
    }

    public List<Grade> getGradesByStudent(long id){
        return repository.findByStudent_StudentId(id);
    }
    public List<Grade> getGradesByStudentAndCourse(long studentId,long courseId){
        return repository.findByStudent_StudentIdAndCourseId(studentId,courseId);
    }
    public String callCourseService(long id) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8095/api/v1/courses/department_id/{id]}",
                HttpMethod.GET,
                entity,
                String.class,
                id);

        String responseBody = response.getBody();
        System.out.println(responseBody);
        return responseBody;
    }
    public String findCourseRequire(long courseId,long studentId){
        return repository.findCourseByCourse(courseId,studentId);
    }
    public boolean checkPrerequisites(long studentId, long courseId){
        Optional<Double> grade = repository.findGrade(studentId,courseId);
        if (grade.isPresent()) {
            return grade.get() >= 3;
        }
        else return false;
    }
}
