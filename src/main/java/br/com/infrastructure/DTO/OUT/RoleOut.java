package br.com.infrastructure.DTO.OUT;

import br.com.domain.entities.Role;
import java.lang.module.ModuleDescriptor;
import java.util.List;
import java.util.Optional;

public record RoleOut(int id) {

	public static RoleOut toOut(Role domain){
		return new RoleOut(domain.getId());
	}

	public static Optional<RoleOut> outOptional(Optional<Role> domain){
		return Optional.of(new RoleOut(domain.get().getId()));
	}

}
