package com.nisum.challenge.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "El correo no tiene un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "${app.regex.password}", message = "La contraseña no cumple con el formato requerido")
    private String password;

    @NotNull(message = "La lista de teléfonos no puede ser nula")
    @Size(min = 1, message = "Debe ingresar al menos un teléfono")
    private List<TelefonoRequest> phones;
}
