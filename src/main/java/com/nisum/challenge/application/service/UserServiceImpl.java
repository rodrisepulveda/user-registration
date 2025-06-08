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
	public User registrarUsuario(User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("El correo ya esta registrado.");
		}

		UUID id = UUID.randomUUID();
		LocalDateTime now = LocalDateTime.now();
		String token = jwtUtil.generateToken(id, user.getEmail());

		user.setId(id);
		user.setCreated(now);
		user.setModified(now);
		user.setLastLogin(now);
		user.setToken(token);
		user.setActive(true);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User updateActiveStatus(UUID id, boolean active) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		user.setActive(active);
		return userRepository.save(user);
	}

	@Override
	public User getById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
	}

}
