package com.example.issueTrackerOrm.mapper;
import com.example.issueTrackerOrm.domain.Bug;
import com.example.issueTrackerOrm.domain.Project;
import com.example.issueTrackerOrm.dto.BugRequestDto;
import com.example.issueTrackerOrm.dto.BugResponseDto;

public class BugMapper {
        public static Bug toEntity(BugRequestDto requestDto,Project project) {
        if (requestDto == null) {
            return null;
        }
        Bug bug = new Bug();
        bug.setTitle(requestDto.getTitle());
        bug.setDescription(requestDto.getDescription());
        bug.setStatus(requestDto.getStatus());
        bug.setPriority(requestDto.getPriority());
        bug.setProject(project);
        return bug;
    }
    public static BugResponseDto toResponseDto(Bug b) {
        if (b == null) {
            return null;
        }
        BugResponseDto responseDto = new BugResponseDto();
        responseDto.setBugid(b.getBugid());
        responseDto.setTitle(b.getTitle());
        responseDto.setDescription(b.getDescription());
        responseDto.setStatus(b.getStatus());
        responseDto.setPriority(b.getPriority());
        responseDto.setProjectId(b.getProject() != null ? b.getProject().getId() : null);
        responseDto.setProjectName(b.getProject() != null ? b.getProject().getName() : null);
        return responseDto;
    }
}
