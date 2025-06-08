package com.nisum.challenge.application.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nisum.challenge.domain.exception.EmailAlreadyExistsException;
import com.nisum.challenge.domain.exception.NotFoundException;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.domain.repository.UserRepositoryPort;
import com.nisum.challenge.domain.service.UserService;
import com.nisum.challenge.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepositoryPort userRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User registrarUsuario(User usuario) {
		if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("El correo ya esta registrado.");
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

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		return userRepository.save(usuario);
	}

	@Override
	public User updateActiveStatus(UUID id, boolean active) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		user.setActive(active);
		return userRepository.save(user);
	}

}
