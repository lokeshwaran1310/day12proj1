package com.example.issueTrackerOrm.service;

import java.util.List;

import com.example.issueTrackerOrm.domain.User;

public interface UserService {

    User createUser(User user);
    User findByUsername(String username);
    User findByUserId(long userId);
    void deleteByUserId(long userId);
    User updateUser(User user);
    List<User> getAllUsers(); 
    
}
