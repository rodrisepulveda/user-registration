package com.nisum.challenge.adapter.in.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nisum.challenge.adapter.in.mapper.UserRequestMapper;
import com.nisum.challenge.adapter.in.mapper.UserResponseMapper;
import com.nisum.challenge.domain.exception.NotFoundException;
import com.nisum.challenge.domain.service.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerGetUserByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRequestMapper requestMapper;

    @MockBean
    private UserResponseMapper responseMapper;
    
    @Test
    void getUserById_usuarioNoExiste_retorna404() throws Exception {
        UUID id = UUID.randomUUID();
        when(userService.getById(id)).thenThrow(new NotFoundException("User not found"));

        mockMvc.perform(get("/api/users/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    void getUserById_idMalFormado_retorna400() throws Exception {
        String invalidId = "abc-invalid-uuid";

        mockMvc.perform(get("/api/users/" + invalidId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid user ID format"));
    }

    @Test
    void updateActiveStatus_conCampoActiveNulo_retorna400() throws Exception {
        // Campo active faltante en el JSON
        String body = "{}";
        UUID userId = UUID.randomUUID();

        mockMvc.perform(patch("/api/users/{id}/active", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
    
}
