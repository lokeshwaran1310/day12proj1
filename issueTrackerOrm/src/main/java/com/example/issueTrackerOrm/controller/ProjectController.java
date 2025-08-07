package com.example.issueTrackerOrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.issueTrackerOrm.domain.Project;
import com.example.issueTrackerOrm.service.ProjectServiceImpl;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projimpl;
    @GetMapping("/all")
     List<Project> getallproject(){
        return projimpl.getallproject();
    }
    @PostMapping("/create")
public Project createProject(@RequestBody Project project){
    if(project == null || project.getName() == null ) {
        throw new IllegalArgumentException("Project name and description cannot be null");
    }
    return projimpl.createProject(project);
}

    @GetMapping("/find/name/{name}")
    public Project findByName(@PathVariable String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null or empty");
        }
        return projimpl.findByName(name);
    }
    @GetMapping("/find/id/{projectId}")
    public Project findByProjectId(@PathVariable long projectId){
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be a positive number");
        }
       return projimpl.findByProjectId(projectId);
    }
  @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable long projectId) {
         if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be a positive number");
        }
        projimpl.deleteByProjectId(projectId);
        return ResponseEntity.ok("Deleted project with ID: " + projectId);
    }
   @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        Project updatedProject = projimpl.updateProject(project);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/bugcount/{projectId}")
    public int getBugCount(@PathVariable long projectId) {
         if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be a positive number");
        }
        return projimpl.getBugCount(projectId);
    }
    @GetMapping("/all/names")
    public List<String> listAllProjectNames() {
        List<String> projectNames = projimpl.listAllProjectNames();
        if (projectNames.isEmpty()) {
            throw new IllegalArgumentException("No projects found");
        }
        return projectNames;
    }
    
}
