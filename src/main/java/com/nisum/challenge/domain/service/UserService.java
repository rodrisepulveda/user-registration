package com.nisum.challenge.domain.service;

import java.util.UUID;

import com.nisum.challenge.domain.model.User;

public interface UserService {
	User registrarUsuario(User usuario);

	User updateActiveStatus(UUID id, boolean isActive);
}
