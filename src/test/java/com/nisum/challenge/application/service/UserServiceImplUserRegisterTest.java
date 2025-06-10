package com.nisum.challenge.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nisum.challenge.domain.exception.EmailAlreadyExistsException;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.domain.repository.UserRepositoryPort;
import com.nisum.challenge.infrastructure.security.JwtUtil;

class UserServiceImplUserRegisterTest {

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_conDatosValidos_registraYRetornaUsuario() {
        // Arrange
        String email = "rodri@correo.com";
        String password = "Password123!";
        String encodedPassword = "encodedPassword";
        UUID generatedId = UUID.randomUUID();
        String token = "token-generado";

        User inputUser = User.builder().name("Rodrigo").email(email).password(password).build();
        User savedUser = User.builder()
                .id(generatedId)
                .name("Rodrigo")
                .email(email)
                .password(encodedPassword)
                .token(token)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(jwtUtil.generateToken(any(UUID.class), eq(email))).thenReturn(token);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.registerUser(inputUser);

        // Assert
        assertNotNull(result.getId());
        assertEquals(email, result.getEmail());
        assertEquals(encodedPassword, result.getPassword());
        assertEquals(token, result.getToken());
        assertTrue(result.isActive());
        assertNotNull(result.getCreated());
        assertNotNull(result.getModified());
        assertNotNull(result.getLastLogin());
    }

    @Test
    void registrarUsuario_conEmailYaRegistrado_lanzaExcepcion() {
        // Arrange
        String email = "rodri@correo.com";
        User existingUser = User.builder().email(email).build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        User newUser = User.builder().name("Rodrigo").email(email).password("1234").build();

        // Act & Assert
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(newUser)
        );

        assertEquals("The email is already registered.", exception.getMessage());
        verify(userRepository, never()).save(any());
    }
}
