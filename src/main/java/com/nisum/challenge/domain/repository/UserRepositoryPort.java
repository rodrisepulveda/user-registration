package com.nisum.challenge.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.nisum.challenge.domain.model.User;

public interface UserRepositoryPort {
	Optional<User> findByEmail(String email);

	User save(User usuario);

	Optional<User> findById(UUID id);
}
