package com.nisum.challenge.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "telefono")
public class TelefonoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
