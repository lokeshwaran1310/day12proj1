package com.example.studentcourse.service;

import java.util.List;

import com.example.studentcourse.dto.CourseRequestDto;
import com.example.studentcourse.dto.CourseResponseDto;

public interface CourseService {
    CourseResponseDto findById(Long id);
    List<CourseResponseDto> findAll();
    CourseResponseDto save(CourseRequestDto courseRequestDto);
    void deleteById(Long id);
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);
    List<CourseResponseDto> findCoursesWithMoreThanThreeEnrollments();
    CourseResponseDto findByTitle(String title);
}
