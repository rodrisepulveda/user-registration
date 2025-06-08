package com.nisum.challenge.dto;

import java.util.List;

import com.nisum.challenge.infrastructure.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

    @NotBlank(message = "El nombre es obligatorio")
    String name,

    @Email(message = "El correo no tiene un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @ValidPassword
    String password,

    @NotNull(message = "La lista de teléfonos no puede ser nula")
    @Size(min = 1, message = "Debe ingresar al menos un teléfono")
    List<PhoneRequest> phones

) {}
