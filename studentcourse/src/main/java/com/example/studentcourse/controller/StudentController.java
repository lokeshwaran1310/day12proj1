package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentRequestDto;
import com.example.studentcourse.dto.StudentResponseDto;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable @Positive Long id) {
        return studentService.findById(id);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.save(studentRequestDto);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable @Positive Long id, @RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable @Positive Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/email")
    public StudentResponseDto getByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }
    
    @GetMapping("/search/name")
    public StudentResponseDto getByName(@RequestParam String name) {
        return studentService.findByName(name);
    }
    
    @GetMapping("/more-than-three-enrollments")
    public List<StudentResponseDto> getStudentsWithMoreThanThreeEnrollments() {
        return studentService.getStudentsWithMoreThanThreeEnrollments();
    }
}
