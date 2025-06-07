package com.nisum.challenge.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
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
