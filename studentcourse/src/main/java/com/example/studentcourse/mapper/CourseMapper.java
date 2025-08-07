package com.example.studentcourse.mapper;
import com.example.studentcourse.domain.Course;
import com.example.studentcourse.dto.CourseRequestDto;
import com.example.studentcourse.dto.CourseResponseDto;

public class CourseMapper {
    
    public static Course toCourseEntity(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());
        return course;
    }
    
    public static CourseResponseDto toCourseResponseDto(Course course) {
        CourseResponseDto courseResponse = new CourseResponseDto();
        courseResponse.setId(course.getId());
        courseResponse.setTitle(course.getTitle());
        courseResponse.setDescription(course.getDescription());
        return courseResponse;
    }
}
