package com.example.issueTrackerOrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.issueTrackerOrm.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
    @Query("SELECT COUNT(b) FROM Bug b WHERE b.project.id = :projectId")
    int countBugsByProjectId(@Param("projectId") long projectId);
    @Query("SELECT p.name FROM Project p")
    List<String> getAllProjectNames();
    
}
