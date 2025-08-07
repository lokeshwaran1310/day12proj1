package com.example.studentcourse.controller;

import com.example.studentcourse.dto.CourseRequestDto;
import com.example.studentcourse.dto.CourseResponseDto;
import com.example.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public CourseResponseDto getCourseById(@PathVariable @Positive Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping
    public CourseResponseDto createCourse(@RequestBody @Valid CourseRequestDto courseRequestDto) {
        return courseService.save(courseRequestDto);
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@PathVariable @Positive Long id, @RequestBody @Valid CourseRequestDto courseRequestDto) {
        return courseService.updateCourse(id, courseRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable @Positive Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public CourseResponseDto getByTitle(@RequestParam String title) {
        return courseService.findByTitle(title);
    }

    @GetMapping("/more-than-three-enrollments")
    public List<CourseResponseDto> getCoursesWithMoreThanThreeEnrollments() {
        return courseService.findCoursesWithMoreThanThreeEnrollments();
    }
}
