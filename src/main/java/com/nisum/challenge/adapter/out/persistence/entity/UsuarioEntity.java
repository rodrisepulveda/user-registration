package com.nisum.challenge.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario", indexes = { @Index(name = "idx_usuario_email", columnList = "email") })
public class UsuarioEntity {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;

	private String token;
	private boolean isActive;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<TelefonoEntity> phones;
}
