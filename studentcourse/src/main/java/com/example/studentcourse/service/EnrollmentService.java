package com.example.studentcourse.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentcourse.domain.Enrollment;

public interface EnrollmentService {

    Enrollment findById(Long id);
    List<Enrollment> findAll();
    Enrollment save(Enrollment enrollment);
    void deleteById(Long id);
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    long countEnrollmentsByCourseId(Long courseId);
    List<Enrollment> getEnrollmentsOnSameDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

     
}
