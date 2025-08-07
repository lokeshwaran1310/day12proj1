package com.example.issueTrackerOrm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.issueTrackerOrm.domain.Bug;
import com.example.issueTrackerOrm.dto.BugRequestDto;
public interface BugRepository extends JpaRepository<Bug, Long>{
    List<Bug> findByStatus(String status);
    // List<Bug> findByPriorityOrder(String priority);
    List<Bug> findByProjectId(long project_id);
    List<Bug> findByAssignedToId(int userid);
    Bug findById(long id);
    Bug save(Bug bug);
    @Query("SELECT b FROM Bug b WHERE b.assignedTo.id = :userid AND b.status <> 'resolved'")
    List<Bug> findUnresolvedBugsByUserId(@Param("userid") Long userid);

    @Query("SELECT COUNT(b) FROM Bug b WHERE b.project.id = :projectId")
    Long countBugsByProjectId(Long projectId); 
    


}
