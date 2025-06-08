package com.nisum.challenge.adapter.in.mapper;

import org.springframework.stereotype.Component;

import com.nisum.challenge.domain.model.User;
import com.nisum.challenge.dto.PhoneResponse;
import com.nisum.challenge.dto.UserCreatedResponse;
import com.nisum.challenge.dto.UserDetailsResponse;

@Component
public class UserResponseMapper {

	public UserCreatedResponse toResponse(User user) {
		UserCreatedResponse response = new UserCreatedResponse();
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setActive(user.isActive());
		return response;
	}

	public UserDetailsResponse toUserDetailsResponse(User user) {
	    return new UserDetailsResponse(
	        user.getId(),
	        user.getName(),
	        user.getCreated(),
	        user.getModified(),
	        user.getLastLogin(),
	        user.isActive(),
	        user.getPhones().stream()
	            .map(phone -> new PhoneResponse(
	                phone.getNumber(),
	                phone.getCityCode(),
	                phone.getCountryCode()))
	            .toList()
	    );
	}


}
