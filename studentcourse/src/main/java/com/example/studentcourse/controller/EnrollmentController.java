package com.example.studentcourse.controller;

import com.example.studentcourse.domain.Enrollment;
import com.example.studentcourse.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController  {

    @Autowired
    private EnrollmentService enrollmentservice;

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Enrollment enrollment = enrollmentservice.findById(id);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentservice.findAll();
    }

   @PostMapping
public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment) {
    if (enrollment == null ||
        enrollment.getEnrollmentdate() == null ||
        enrollment.getStudent() == null || enrollment.getStudent().getId() == null || enrollment.getStudent().getId() <= 0 ||
        enrollment.getCourse() == null || enrollment.getCourse().getId() == null || enrollment.getCourse().getId() <= 0) {
        return ResponseEntity.badRequest().body("Invalid enrollment input");
    }

    Enrollment saved = enrollmentservice.save(enrollment);
    return ResponseEntity.ok(saved);
}


    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        if (id == null || id <= 0 ||
            enrollment == null ||
            enrollment.getStudent() == null || enrollment.getStudent().getId() == null || enrollment.getStudent().getId() <= 0 ||
            enrollment.getCourse() == null || enrollment.getCourse().getId() == null || enrollment.getCourse().getId() <= 0 ||
            enrollment.getEnrollmentdate() == null) {
            return ResponseEntity.badRequest().body("Invalid update data.");
        }

        Enrollment updated = enrollmentservice.updateEnrollment(id, enrollment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        enrollmentservice.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/same-date")
    public List<Enrollment> getEnrollmentsOnSameDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    if (date == null) {
        return List.of();
    }
    return enrollmentservice.getEnrollmentsOnSameDate(date);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        if (studentId == null || studentId <= 0) {
            return List.of();
        }
        return enrollmentservice.findByStudentId(studentId);
    }
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourseId(@PathVariable Long courseId)
    {
        if (courseId == null || courseId <= 0) {
            return List.of();
        }
        return enrollmentservice.findByCourseId(courseId);
    }
    @GetMapping("/count/course/{courseId}")
    public long countEnrollmentsByCourseId(@PathVariable Long courseId) {
        if (courseId == null || courseId <= 0) {
            return 0;
        }
        return enrollmentservice.countEnrollmentsByCourseId(courseId);
    }

}
