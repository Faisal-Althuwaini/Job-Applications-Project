package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public User registerUser(User user) {
       return userRepository.save(user);
    }


}
