package br.com.infrastructure.DTO.IN;

import br.com.domain.entities.Role;
import br.com.domain.entities.User;
import java.util.List;

public record UserIn(String name, String email, String password, String cpf, List<Byte> roleId) {

	public User toDomainWithRole() {
		return new User(name, email, password, cpf, roleId);
	}
}
