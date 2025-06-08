package com.nisum.challenge.adapter.in.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.challenge.adapter.in.mapper.UserRequestMapper;
import com.nisum.challenge.adapter.in.mapper.UserResponseMapper;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.domain.service.UserService;
import com.nisum.challenge.dto.UserActiveStatusRequest;
import com.nisum.challenge.dto.UserActiveStatusResponse;
import com.nisum.challenge.dto.UserRequest;
import com.nisum.challenge.dto.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserRequestMapper requestMapper;
	private final UserResponseMapper responseMapper;

	@PostMapping
	public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UserRequest request) {
		User user = requestMapper.toDomain(request);
		User registred = userService.registrarUsuario(user);
		UserResponse response = responseMapper.toResponse(registred);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}/active")
	public ResponseEntity<UserActiveStatusResponse> updateActiveStatus(@PathVariable UUID id,
			@RequestBody UserActiveStatusRequest request) {

		User updatedUser = userService.updateActiveStatus(id, request.active());
		UserActiveStatusResponse response = new UserActiveStatusResponse(updatedUser.getId(), updatedUser.getEmail(),
				updatedUser.isActive());

		return ResponseEntity.ok(response);
	}

}
