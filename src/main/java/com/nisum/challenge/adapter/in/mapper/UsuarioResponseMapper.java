package com.nisum.challenge.adapter.in.mapper;

import com.nisum.challenge.domain.model.Usuario;
import com.nisum.challenge.dto.UsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setCreated(usuario.getCreated());
        response.setModified(usuario.getModified());
        response.setLastLogin(usuario.getLastLogin());
        response.setToken(usuario.getToken());
        response.setActive(usuario.isActive());
        return response;
    }
}
