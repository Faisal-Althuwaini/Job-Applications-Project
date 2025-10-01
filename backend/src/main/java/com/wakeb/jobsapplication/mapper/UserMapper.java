package com.wakeb.jobsapplication.mapper;

import com.wakeb.jobsapplication.dto.UserDTO;
import com.wakeb.jobsapplication.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getName(),
                user.getEmail()
        );
    }
}
