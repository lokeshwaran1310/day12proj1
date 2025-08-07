package com.example.issueTrackerOrm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.issueTrackerOrm.domain.Bug;
import com.example.issueTrackerOrm.domain.Project;
import com.example.issueTrackerOrm.dto.BugRequestDto;
import com.example.issueTrackerOrm.dto.BugResponseDto;
import com.example.issueTrackerOrm.exception.EmptyResultException;
import com.example.issueTrackerOrm.exception.InvalidRequestException;
import com.example.issueTrackerOrm.exception.ResourceNotFoundException;
import com.example.issueTrackerOrm.mapper.BugMapper;
import com.example.issueTrackerOrm.repository.BugRepository;
import com.example.issueTrackerOrm.repository.ProjectRepository;

@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;
    
    @Autowired
    private ProjectRepository projectRepository;

    public List<BugResponseDto> getAllBugs(){
        List<Bug> bugs = bugRepository.findAll();
        if(bugs.isEmpty()) {
            throw new EmptyResultException("No bugs found");
        }
        return bugs.stream()
                .map(BugMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<BugResponseDto> findByStatus(String status){
        if(status == null || status.trim().isEmpty()) {
            throw new InvalidRequestException("Status cannot be null or empty");
        }
        List<Bug> bugs = bugRepository.findByStatus(status);
        return bugs.stream()
                .map(BugMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<BugResponseDto> findByProjectId(long  project_id){
        List<Bug> bugs = bugRepository.findByProjectId(project_id);
        return bugs.stream()
                .map(BugMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<BugResponseDto> findByAssignedToId(int userid){
        List<Bug> bugs = bugRepository.findByAssignedToId(userid);
        return bugs.stream()
                .map(BugMapper::toResponseDto)
                .collect(Collectors.toList());
    }
     public List<BugResponseDto> findUnresolvedBugsByUserId(@Param("userid") Long userid){
        List<Bug> bugs = bugRepository.findUnresolvedBugsByUserId(userid);
        return bugs.stream()
                .map(BugMapper::toResponseDto)
                .collect(Collectors.toList());
     }
     public Long countBugsByProjectId(Long projectId){
        return bugRepository.countBugsByProjectId(projectId);
     }

    public BugResponseDto findById(long id){
        Bug bug = bugRepository.findById(id);
        if(bug == null) {
            throw new ResourceNotFoundException("Bug not found with id: " + id);
        }
        return BugMapper.toResponseDto(bug);
    }
    
    public BugResponseDto save(BugRequestDto bugRequestDto){
        if(bugRequestDto == null) {
            throw new InvalidRequestException("Bug request cannot be null");
        }
        Project project = projectRepository.findById(bugRequestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + bugRequestDto.getProjectId()));
        
        Bug bug = BugMapper.toEntity(bugRequestDto, project);
        Bug savedBug = bugRepository.save(bug);
        return BugMapper.toResponseDto(savedBug);
    }
    public void deleteById(long id){
        Bug existingBug = bugRepository.findById(id);
        if(existingBug == null) {
            throw new ResourceNotFoundException("Bug not found with id: " + id);
        }
        bugRepository.deleteById(id);
    }
    public BugResponseDto updateBug(long id, BugRequestDto bugRequestDto){
        if(bugRequestDto == null) {
            throw new InvalidRequestException("Bug request cannot be null");
        }
        Bug existingBug = bugRepository.findById(id);
        if(existingBug == null){
            throw new ResourceNotFoundException("Bug not found with id: " + id);
        }
        
        Project project = projectRepository.findById(bugRequestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + bugRequestDto.getProjectId()));
        
        existingBug.setTitle(bugRequestDto.getTitle());
        existingBug.setDescription(bugRequestDto.getDescription());
        existingBug.setStatus(bugRequestDto.getStatus());
        existingBug.setPriority(bugRequestDto.getPriority());
        existingBug.setProject(project);
        
        Bug updatedBug = bugRepository.save(existingBug);
        return BugMapper.toResponseDto(updatedBug);
    }
}
