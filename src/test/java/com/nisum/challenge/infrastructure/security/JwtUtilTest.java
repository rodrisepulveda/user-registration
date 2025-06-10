package com.nisum.challenge.infrastructure.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String secret = "12345678901234567890123456789012"; // Debe tener al menos 32 bytes para HS256
    private final long expirationMillis = 3600000; // 1 hora

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secret, expirationMillis);
    }

    @Test
    void generateToken_tokenEsValidoYContieneDatosCorrectos() {
        UUID userId = UUID.randomUUID();
        String email = "test@correo.com";

        String token = jwtUtil.generateToken(userId, email);

        assertNotNull(token);
        assertTrue(jwtUtil.isTokenValid(token));

        Claims claims = jwtUtil.getClaims(token);
        assertEquals(email, claims.getSubject());
        assertEquals(userId.toString(), claims.get("userId"));
    }

    @Test
    void isTokenValid_tokenInvalido_retornaFalse() {
        String tokenInvalido = "este-no-es-un-token";

        boolean esValido = jwtUtil.isTokenValid(tokenInvalido);

        assertFalse(esValido);
    }

    @Test
    void getClaims_tokenInvalido_lanzaExcepcion() {
        String tokenInvalido = "invalido";

        assertThrows(Exception.class, () -> jwtUtil.getClaims(tokenInvalido));
    }
}
