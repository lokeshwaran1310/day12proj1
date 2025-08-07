package com.example.studentcourse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcourse.domain.Course;
import com.example.studentcourse.dto.CourseRequestDto;
import com.example.studentcourse.dto.CourseResponseDto;
import com.example.studentcourse.exceptions.DuplicateResourceException;
import com.example.studentcourse.exceptions.InvalidRequestException;
import com.example.studentcourse.exceptions.ResourceNotFoundException;
import com.example.studentcourse.mapper.CourseMapper;
import com.example.studentcourse.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseResponseDto findById(Long id) {
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Course ID must be positive");
        }
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return CourseMapper.toCourseResponseDto(course);
    }

    @Override
    public List<CourseResponseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseMapper::toCourseResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto save(CourseRequestDto courseRequestDto) {
        if(courseRequestDto == null) {
            throw new InvalidRequestException("Course request cannot be null");
        }
        
        // Check for duplicate title
        if(courseRepository.findByTitle(courseRequestDto.getTitle()) != null) {
            throw new DuplicateResourceException("Course with title already exists: " + courseRequestDto.getTitle());
        }
        
        Course course = CourseMapper.toCourseEntity(courseRequestDto);
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.toCourseResponseDto(savedCourse);
    }

    @Override
    public void deleteById(Long id) {
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Course ID must be positive");
        }
        if(!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        if(id == null || id <= 0) {
            throw new InvalidRequestException("Course ID must be positive");
        }
        if(courseRequestDto == null) {
            throw new InvalidRequestException("Course request cannot be null");
        }
        
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        
        // Check for duplicate title (excluding current course)
        Course courseWithTitle = courseRepository.findByTitle(courseRequestDto.getTitle());
        if(courseWithTitle != null && !courseWithTitle.getId().equals(id)) {
            throw new DuplicateResourceException("Title already exists: " + courseRequestDto.getTitle());
        }
        
        existingCourse.setTitle(courseRequestDto.getTitle());
        existingCourse.setDescription(courseRequestDto.getDescription());
        Course updatedCourse = courseRepository.save(existingCourse);
        return CourseMapper.toCourseResponseDto(updatedCourse);
    }
    
    @Override
    public CourseResponseDto findByTitle(String title){
        if(title == null || title.trim().isEmpty()) {
            throw new InvalidRequestException("Course title cannot be null or empty");
        }
        Course course = courseRepository.findByTitle(title);
        if(course == null) {
            throw new ResourceNotFoundException("Course not found with title: " + title);
        }
        return CourseMapper.toCourseResponseDto(course);
    }
    
    @Override
    public List<CourseResponseDto> findCoursesWithMoreThanThreeEnrollments() {
        List<Course> courses = courseRepository.findCoursesWithMoreThanThreeEnrollments();
        return courses.stream()
                .map(CourseMapper::toCourseResponseDto)
                .collect(Collectors.toList());
    }
}
