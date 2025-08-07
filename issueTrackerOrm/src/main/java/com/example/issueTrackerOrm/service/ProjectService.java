package com.example.issueTrackerOrm.service;

import java.util.List;

import com.example.issueTrackerOrm.domain.Project;

public interface ProjectService {
    List<Project> getallproject();
    Project createProject(Project project);
    Project findByName(String name);
    Project findByProjectId(long projectId);
    void deleteByProjectId(long projectId);
    int getBugCount(long projectId);
    List<String> listAllProjectNames();
    Project updateProject(Project project);
    
}
