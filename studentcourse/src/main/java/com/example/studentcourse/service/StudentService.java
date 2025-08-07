package com.example.studentcourse.service;

import java.util.List;

import com.example.studentcourse.dto.StudentRequestDto;
import com.example.studentcourse.dto.StudentResponseDto;

public interface StudentService {
    StudentResponseDto findById(Long id);
    List<StudentResponseDto> findAll();
    StudentResponseDto save(StudentRequestDto studentRequestDto);
    void deleteById(Long id);
    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);
    StudentResponseDto findByName(String name);
    StudentResponseDto findByEmail(String email);
    List<StudentResponseDto> getStudentsWithMoreThanThreeEnrollments();
}
