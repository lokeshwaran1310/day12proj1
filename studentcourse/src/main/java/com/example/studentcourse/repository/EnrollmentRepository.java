package com.example.studentcourse.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentcourse.domain.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.id = :courseId")
    long countEnrollmentsByCourseId(@Param("courseId") Long courseId);
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentdate = :date")
    List<Enrollment> getEnrollmentsOnSameDate(@Param("date") LocalDate date);

}
