package com.nisum.challenge.domain.repository;

import com.nisum.challenge.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
}
