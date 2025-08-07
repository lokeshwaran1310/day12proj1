package com.example.issueTrackerOrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issueTrackerOrm.domain.Project;
import com.example.issueTrackerOrm.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projrepo;

    @Override
    public List<Project> getallproject() {
        return projrepo.findAll();
    }

    @Override
    public Project createProject(Project project) {
        return projrepo.save(project);
    }

    @Override
    public Project findByName(String name) {
        return projrepo.findByName(name);
    }

    @Override
    public Project findByProjectId(long projectId) {
        return projrepo.findById(projectId).orElse(null);
    }

    @Override
    public void deleteByProjectId(long projectId) {
        projrepo.deleteById(projectId);
    }
    @Override
    public int getBugCount(long projectId) {
       return projrepo.countBugsByProjectId(projectId);
}
    @Override
    public List<String> listAllProjectNames() {
        return projrepo.getAllProjectNames();
    }
    @Override
    public Project updateProject(Project project) {
        Project existingProject = projrepo.findById(project.getId()).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            return projrepo.save(existingProject);
        }
        return null; 
    }

}
