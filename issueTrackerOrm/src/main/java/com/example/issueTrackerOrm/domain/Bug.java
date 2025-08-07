package com.example.issueTrackerOrm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bugs")
public class Bug {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bugid;
    private String title;
    private String description;
    private String status;
    private String priority;
   
    @ManyToOne
    @JoinColumn(name="project_ID")
    @JsonBackReference(value="project-bugs")
    private Project project;
     @ManyToOne
    @JoinColumn(name="assigned_to")
    @JsonBackReference(value="user-bugs")
    private User assignedTo;
    public long getBugid() {
        return bugid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setBugid(long bugid) {
        this.bugid = bugid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
    

    
}
