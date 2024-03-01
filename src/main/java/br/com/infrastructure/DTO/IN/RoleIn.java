package br.com.infrastructure.DTO.IN;

import br.com.domain.entities.Role;

public record RoleIn(String name) {

	public Role toDomain() {
		return new Role(name);
	}

	public  Role toDomain(int id){
		return new Role(id,name);
	}
}
