package com.nisum.challenge.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).headers(headers -> headers.frameOptions().disable()) // ← necesario para H2
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users").permitAll()
						.requestMatchers("/h2-console/**").permitAll() // ← permite consola H2
						.anyRequest().authenticated());

		return http.build();
	}
}
