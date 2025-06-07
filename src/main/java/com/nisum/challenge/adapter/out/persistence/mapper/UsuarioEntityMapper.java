package com.nisum.challenge.adapter.out.persistence.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nisum.challenge.adapter.out.persistence.entity.TelefonoEntity;
import com.nisum.challenge.adapter.out.persistence.entity.UsuarioEntity;
import com.nisum.challenge.domain.model.Telefono;
import com.nisum.challenge.domain.model.Usuario;

@Component
public class UsuarioEntityMapper {

	public Usuario toDomain(UsuarioEntity entity) {
		Usuario usuario = new Usuario();
		usuario.setId(entity.getId());
		usuario.setName(entity.getName());
		usuario.setEmail(entity.getEmail());
		usuario.setPassword(entity.getPassword());
		usuario.setCreated(entity.getCreated());
		usuario.setModified(entity.getModified());
		usuario.setLastLogin(entity.getLastLogin());
		usuario.setToken(entity.getToken());
		usuario.setActive(entity.isActive());

		if (entity.getPhones() != null) {
			usuario.setPhones(entity.getPhones().stream().map(this::mapTelefono).collect(Collectors.toList()));
		}

		return usuario;
	}

	public UsuarioEntity toEntity(Usuario domain) {
		UsuarioEntity entity = new UsuarioEntity();
		entity.setId(domain.getId());
		entity.setName(domain.getName());
		entity.setEmail(domain.getEmail());
		entity.setPassword(domain.getPassword());
		entity.setCreated(domain.getCreated());
		entity.setModified(domain.getModified());
		entity.setLastLogin(domain.getLastLogin());
		entity.setToken(domain.getToken());
		entity.setActive(domain.isActive());

		if (domain.getPhones() != null) {
			List<TelefonoEntity> telefonos = domain.getPhones().stream().map(this::mapTelefonoEntity)
					.collect(Collectors.toList());

			telefonos.forEach(t -> t.setUsuario(entity));
			entity.setPhones(telefonos);
		}

		return entity;
	}

	private Telefono mapTelefono(TelefonoEntity entity) {
		Telefono tel = new Telefono();
		tel.setNumber(entity.getNumber());
		tel.setCitycode(entity.getCitycode());
		tel.setContrycode(entity.getContrycode());
		return tel;
	}

	private TelefonoEntity mapTelefonoEntity(Telefono domain) {
		TelefonoEntity entity = new TelefonoEntity();
		entity.setNumber(domain.getNumber());
		entity.setCitycode(domain.getCitycode());
		entity.setContrycode(domain.getContrycode());
		return entity;
	}
}
