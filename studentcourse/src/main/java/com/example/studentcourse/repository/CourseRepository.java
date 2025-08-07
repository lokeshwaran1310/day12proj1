package com.example.studentcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studentcourse.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByTitle(String title);
    @Query("SELECT c FROM Course c WHERE SIZE(c.enrollments) > 3")
    List<Course> findCoursesWithMoreThanThreeEnrollments();

    
}
