package com.nisum.challenge.adapter.in.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nisum.challenge.domain.model.Phone;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.CreateUserRequest;
import com.nisum.challenge.dto.PhoneRequest;

@Component
public class UserRequestMapper {

    public User toDomain(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        if (request.phones() != null) {
            List<Phone> phones = request.phones().stream()
                    .map(this::mapTelefono)
                    .collect(Collectors.toList());
            user.setPhones(phones);
        }

        return user;
    }

    private Phone mapTelefono(PhoneRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getNumber());
        phone.setCityCode(request.getCitycode());
        phone.setCountryCode(request.getContrycode());
        return phone;
    }
}
