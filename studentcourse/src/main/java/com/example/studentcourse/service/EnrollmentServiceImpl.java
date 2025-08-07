package com.example.studentcourse.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentcourse.domain.Enrollment;
import com.example.studentcourse.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        Enrollment existing = findById(id);
        if (existing != null) {
            existing.setEnrollmentdate(enrollment.getEnrollmentdate());
            existing.setStudent(enrollment.getStudent());
            existing.setCourse(enrollment.getCourse());
            return enrollmentRepository.save(existing);
        }
        return null;
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> findByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public long countEnrollmentsByCourseId(Long courseId){
        return enrollmentRepository.countEnrollmentsByCourseId(courseId);
    }
   @Override
   public List<Enrollment> getEnrollmentsOnSameDate(LocalDate date) {
    return enrollmentRepository.getEnrollmentsOnSameDate(date);
   }
}
