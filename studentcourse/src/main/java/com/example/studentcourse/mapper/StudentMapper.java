package com.example.studentcourse.mapper;

import com.example.studentcourse.domain.Student;
import com.example.studentcourse.dto.StudentRequestDto;
import com.example.studentcourse.dto.StudentResponseDto;

public class StudentMapper {

    public static Student toStudentEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        return student;
    }

    public static StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto studentResponse = new StudentResponseDto();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setEmail(student.getEmail());
        return studentResponse;
    }
    
}