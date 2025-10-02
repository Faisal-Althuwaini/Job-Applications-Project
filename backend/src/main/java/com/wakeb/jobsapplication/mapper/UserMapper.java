package com.wakeb.jobsapplication.mapper;

import com.wakeb.jobsapplication.dto.UserDTO;
import com.wakeb.jobsapplication.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setDeleteAt(user.getDeletedAt());
        return dto;
    }
}
