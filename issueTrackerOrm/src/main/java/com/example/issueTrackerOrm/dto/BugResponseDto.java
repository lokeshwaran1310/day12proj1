package com.example.issueTrackerOrm.dto;
public class BugResponseDto {
    private long bugid;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long projectId;
    private String projectName;

    public long getBugid() {
        return bugid;
    }

    public void setBugid(long bugid) {
        this.bugid = bugid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    

    
}
