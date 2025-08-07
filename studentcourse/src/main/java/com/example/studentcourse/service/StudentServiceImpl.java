package com.example.studentcourse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcourse.domain.Student;
import com.example.studentcourse.dto.StudentRequestDto;
import com.example.studentcourse.dto.StudentResponseDto;
import com.example.studentcourse.exceptions.DuplicateResourceException;
import com.example.studentcourse.exceptions.InvalidRequestException;
import com.example.studentcourse.exceptions.ResourceNotFoundException;
import com.example.studentcourse.mapper.StudentMapper;
import com.example.studentcourse.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentrepository;
    
    @Override
    public StudentResponseDto findById(Long id){
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Student ID must be positive");
        }
        Student student = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return StudentMapper.toStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> findAll(){
        List<Student> students = studentrepository.findAll();
        return students.stream()
                .map(StudentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto save(StudentRequestDto studentRequestDto){
        if(studentRequestDto == null) {
            throw new InvalidRequestException("Student request cannot be null");
        }
        
    
        if(studentrepository.findByEmail(studentRequestDto.getEmail()) != null) {
            throw new DuplicateResourceException("Student with email already exists: " + studentRequestDto.getEmail());
        }
        
        Student student = StudentMapper.toStudentEntity(studentRequestDto);
        Student savedStudent = studentrepository.save(student);
        return StudentMapper.toStudentResponseDto(savedStudent);
    }

    @Override
    public void deleteById(Long id){
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Student ID must be positive");
        }
        if(!studentrepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentrepository.deleteById(id);
    }

    @Override
    public StudentResponseDto findByName(String name){
        if(name == null || name.trim().isEmpty()) {
            throw new InvalidRequestException("Student name cannot be null or empty");
        }
        Student student = studentrepository.findByName(name);
        if(student == null) {
            throw new ResourceNotFoundException("Student not found with name: " + name);
        }
        return StudentMapper.toStudentResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto){
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Student ID must be positive");
        }
        if(studentRequestDto == null) {
            throw new InvalidRequestException("Student request cannot be null");
        }
        
        Student existingStudent = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        
        Student studentWithEmail = studentrepository.findByEmail(studentRequestDto.getEmail());
        if(studentWithEmail != null && !studentWithEmail.getId().equals(id)) {
            throw new DuplicateResourceException("Email already exists: " + studentRequestDto.getEmail());
        }
        
        existingStudent.setName(studentRequestDto.getName());
        existingStudent.setEmail(studentRequestDto.getEmail());
        Student updatedStudent = studentrepository.save(existingStudent);
        return StudentMapper.toStudentResponseDto(updatedStudent);
    }

    @Override
    public StudentResponseDto findByEmail(String email){
        if(email == null || email.trim().isEmpty()) {
            throw new InvalidRequestException("Email cannot be null or empty");
        }
        Student student = studentrepository.findByEmail(email);
        if(student == null) {
            throw new ResourceNotFoundException("Student not found with email: " + email);
        }
        return StudentMapper.toStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getStudentsWithMoreThanThreeEnrollments() {
        List<Student> students = studentrepository.getStudentsWithMoreThanThreeEnrollments();
        return students.stream()
                .map(StudentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }
    


    
}
