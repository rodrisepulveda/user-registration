package com.nisum.challenge.domain.service;

import java.util.UUID;

public interface TokenProvider {
    String generateToken(UUID userId, String email);
}
