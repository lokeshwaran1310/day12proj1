package com.example.issueTrackerOrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.issueTrackerOrm.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
     User findByname(String name);
    
}
