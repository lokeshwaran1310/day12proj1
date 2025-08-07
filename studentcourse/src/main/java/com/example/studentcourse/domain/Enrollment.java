package com.example.studentcourse.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="enrollments")
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate enrollmentdate;
    
    @ManyToOne
    @JoinColumn(name="student_id")
    @JsonBackReference("student-enrollments")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name="course_id")
    @JsonBackReference("course-enrollments")
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentdate() {
        return enrollmentdate;
    }

    public void setEnrollmentdate(LocalDate enrollmentdate) {
        this.enrollmentdate = enrollmentdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   
 
    
    
}
