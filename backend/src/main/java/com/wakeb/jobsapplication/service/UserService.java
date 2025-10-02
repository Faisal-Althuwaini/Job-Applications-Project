package com.wakeb.jobsapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wakeb.jobsapplication.dto.UserDTO;
import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.mapper.UserMapper;
import com.wakeb.jobsapplication.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll().stream()
                .filter(user -> user.getDeletedAt() == null)
                .toList();
        return users.stream().map(UserMapper::toDTO).toList();
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return UserMapper.toDTO(user.get());
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> userRepository.deleteByEmail(user.get().getEmail()));
    }
}
