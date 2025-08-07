package com.example.issueTrackerOrm.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String role;
    
    @OneToMany(mappedBy="assignedTo",fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonManagedReference(value="user-bugs")
    private List<Bug> bugs;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
    

    

  

}
