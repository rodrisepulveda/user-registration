package com.nisum.challenge.adapter.in.mapper;

import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseMapper {

    public UserResponse toResponse(User usuario) {
        UserResponse response = new UserResponse();
        response.setId(usuario.getId());
        response.setCreated(usuario.getCreated());
        response.setModified(usuario.getModified());
        response.setLastLogin(usuario.getLastLogin());
        response.setToken(usuario.getToken());
        response.setActive(usuario.isActive());
        return response;
    }
}
