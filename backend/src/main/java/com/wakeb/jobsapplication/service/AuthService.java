package com.wakeb.jobsapplication.service;

import com.wakeb.jobsapplication.dto.AuthResponse;
import com.wakeb.jobsapplication.dto.LoginRequest;
import com.wakeb.jobsapplication.dto.RegisterRequest;
import com.wakeb.jobsapplication.dto.UserDTO;
import com.wakeb.jobsapplication.entity.User;
import com.wakeb.jobsapplication.mapper.UserMapper;
import com.wakeb.jobsapplication.repository.UserRepository;
import com.wakeb.jobsapplication.security.CustomUserDetailsService;
import com.wakeb.jobsapplication.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest loginRequest) {
        return roleLogin(loginRequest, null);
    }

    public AuthResponse adminLogin(LoginRequest loginRequest) {
        return roleLogin(loginRequest, "ADMIN");
    }

    private AuthResponse roleLogin(LoginRequest loginRequest, String role) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));

        if (role != null && !role.equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("تم رفض الوصول: يتطلب صلاحيات : " + user.getRole());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        UserDTO userDTO = UserMapper.toDTO(user);
        return new AuthResponse(token, userDTO);
    }


    public AuthResponse register(RegisterRequest registerRequest) {
        // Check if username already exists
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("اسم المستخدم مستخدم مسبقًا");
        }

        // Create new user
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);

        // Generate JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        UserDTO userDTO = UserMapper.toDTO(savedUser);

        return new AuthResponse(token, userDTO);
    }
}