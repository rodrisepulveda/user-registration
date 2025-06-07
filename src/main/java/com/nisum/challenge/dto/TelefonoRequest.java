package com.nisum.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefonoRequest {

    @NotBlank(message = "El número es obligatorio")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    private String citycode;

    @NotBlank(message = "El código de país es obligatorio")
    private String contrycode;
}
