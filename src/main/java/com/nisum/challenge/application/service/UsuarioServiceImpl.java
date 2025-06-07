package com.nisum.challenge.application.service;

import com.nisum.challenge.domain.model.Usuario;
import com.nisum.challenge.domain.repository.UsuarioRepositoryPort;
import com.nisum.challenge.domain.service.UsuarioService;
import com.nisum.challenge.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepositoryPort usuarioRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya registrado");
        }

        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        String token = jwtUtil.generateToken(id, usuario.getEmail());

        usuario.setId(id);
        usuario.setCreated(now);
        usuario.setModified(now);
        usuario.setLastLogin(now);
        usuario.setToken(token);
        usuario.setActive(true);

        return usuarioRepository.save(usuario);
    }
}
