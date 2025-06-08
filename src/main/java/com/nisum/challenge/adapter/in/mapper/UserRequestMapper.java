package com.nisum.challenge.adapter.in.mapper;

import com.nisum.challenge.domain.model.Phone;
import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.PhoneRequest;
import com.nisum.challenge.dto.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRequestMapper {

    public User toDomain(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        if (request.getPhones() != null) {
            List<Phone> phones = request.getPhones().stream()
                    .map(this::mapTelefono)
                    .collect(Collectors.toList());
            user.setPhones(phones);
        }

        return user;
    }

    private Phone mapTelefono(PhoneRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getNumber());
        phone.setCitycode(request.getCitycode());
        phone.setContrycode(request.getContrycode());
        return phone;
    }
}
