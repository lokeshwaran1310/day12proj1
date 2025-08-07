package com.example.studentcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studentcourse.domain.Student;

public interface StudentRepository extends JpaRepository <Student, Long> {
     public Student findByName(String name);
     public Student findByEmail(String email);
     @Query("SELECT s FROM Student s WHERE SIZE(s.enrollments) > 3")
     List<Student> getStudentsWithMoreThanThreeEnrollments();
}
