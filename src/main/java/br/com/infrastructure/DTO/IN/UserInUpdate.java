package br.com.infrastructure.DTO.IN;

import br.com.domain.entities.User;
import java.util.List;

public record UserInUpdate(String name, String email, String cpf, List<Byte> rolesIds) {

	public User toDomainUpdated(UserInUpdate requestBody) {
		return new User(requestBody.name(), requestBody.email, null, requestBody.cpf, requestBody.rolesIds);
	}
}
