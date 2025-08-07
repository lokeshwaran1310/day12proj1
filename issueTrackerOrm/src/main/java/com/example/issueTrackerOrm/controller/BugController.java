package com.example.issueTrackerOrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import com.example.issueTrackerOrm.dto.BugRequestDto;
import com.example.issueTrackerOrm.dto.BugResponseDto;
import com.example.issueTrackerOrm.service.BugService;

@RestController
@RequestMapping("/api/bugs")
@Validated
public class BugController {
     @Autowired
     private BugService bugService;

     @GetMapping
     public List<BugResponseDto> getAllBugs(){
        return bugService.getAllBugs();
     }
     @GetMapping("/status/{status}")
     public List<BugResponseDto> findByStatus(@PathVariable String status){
        return bugService.findByStatus(status);
    }
    @GetMapping("/project/{project_id}")
    public List<BugResponseDto> findByProjectId(@PathVariable @Positive long project_id) {
        return bugService.findByProjectId(project_id);
    }
    @GetMapping("/assigned-to/{userid}")
    public List<BugResponseDto> findByAssignedToId(@PathVariable @Positive int userid) {
        return bugService.findByAssignedToId(userid);
    }
    @GetMapping("/unresolved/user/{userid}")
    public List<BugResponseDto> findUnresolvedBugsByUserId(@PathVariable @Positive Long userid) {
        return bugService.findUnresolvedBugsByUserId(userid);
    }
    @GetMapping("/count/project/{projectId}")
    public Long countBugsByProjectId(@PathVariable @Positive Long projectId) {
        return bugService.countBugsByProjectId(projectId);
    }
    @GetMapping("/{id}")
    public BugResponseDto findById(@PathVariable @Positive long id){
        return bugService.findById(id);
    }
    @PostMapping("/create")
    public BugResponseDto save(@RequestBody @Valid BugRequestDto bugRequestDto) {
        return bugService.save(bugRequestDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @Positive long id){
        bugService.deleteById(id);
        return ResponseEntity.ok("deleted the Bug Id:"+id);
    }
    @PutMapping("/update/{id}")
    public BugResponseDto updateBug(@PathVariable @Positive long id, @RequestBody @Valid BugRequestDto bugRequestDto){
        return bugService.updateBug(id, bugRequestDto);
    }



    
    

    
    
    
}
