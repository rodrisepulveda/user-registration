package com.nisum.challenge.adapter.in.mapper;

import com.nisum.challenge.domain.model.Phone;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.PhoneRequest;
import com.nisum.challenge.dto.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioRequestMapper {

    public User toDomain(UserRequest request) {
        User usuario = new User();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());

        if (request.getPhones() != null) {
            List<Phone> phones = request.getPhones().stream()
                    .map(this::mapTelefono)
                    .collect(Collectors.toList());
            usuario.setPhones(phones);
        }

        return usuario;
    }

    private Phone mapTelefono(PhoneRequest request) {
        Phone telefono = new Phone();
        telefono.setNumber(request.getNumber());
        telefono.setCitycode(request.getCitycode());
        telefono.setContrycode(request.getContrycode());
        return telefono;
    }
}
