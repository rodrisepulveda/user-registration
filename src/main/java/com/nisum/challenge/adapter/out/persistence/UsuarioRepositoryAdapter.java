package com.nisum.challenge.adapter.out.persistence;

import com.nisum.challenge.adapter.out.persistence.entity.UsuarioEntity;
import com.nisum.challenge.adapter.out.persistence.mapper.UsuarioEntityMapper;
import com.nisum.challenge.adapter.out.persistence.jpa.JpaUsuarioRepository;
import com.nisum.challenge.domain.model.Usuario;
import com.nisum.challenge.domain.repository.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository jpaRepository;
    private final UsuarioEntityMapper mapper;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
