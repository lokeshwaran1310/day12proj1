package com.example.issueTrackerOrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issueTrackerOrm.domain.User;
import com.example.issueTrackerOrm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
   @Override
    public User createUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByname(username);
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findById(userId).orElse(null );
    }

    @Override
    public void deleteByUserId(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }
        return null; 
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
