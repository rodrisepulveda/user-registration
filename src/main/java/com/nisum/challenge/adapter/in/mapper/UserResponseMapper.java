package com.nisum.challenge.adapter.in.mapper;

import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());
        return response;
    }
}
