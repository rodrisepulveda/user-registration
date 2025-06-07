package com.nisum.challenge.adapter.in.controller;

import com.nisum.challenge.adapter.in.mapper.UsuarioRequestMapper;
import com.nisum.challenge.adapter.in.mapper.UsuarioResponseMapper;
import com.nisum.challenge.domain.model.Usuario;
import com.nisum.challenge.domain.service.UsuarioService;
import com.nisum.challenge.dto.UsuarioRequest;
import com.nisum.challenge.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRequestMapper requestMapper;
    private final UsuarioResponseMapper responseMapper;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = requestMapper.toDomain(request);
        Usuario registrado = usuarioService.registrarUsuario(usuario);
        UsuarioResponse response = responseMapper.toResponse(registrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    record ErrorResponse(String mensaje) {}
}
