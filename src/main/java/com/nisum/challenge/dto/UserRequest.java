package com.nisum.challenge.dto;

import java.util.List;

import com.nisum.challenge.infrastructure.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@Email(message = "El correo no tiene un formato válido")
	@NotBlank(message = "El correo es obligatorio")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@ValidPassword
	private String password;

	@NotNull(message = "La lista de teléfonos no puede ser nula")
	@Size(min = 1, message = "Debe ingresar al menos un teléfono")
	private List<PhoneRequest> phones;
}
