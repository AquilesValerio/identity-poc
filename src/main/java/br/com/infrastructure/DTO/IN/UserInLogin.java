package br.com.infrastructure.DTO.IN;

import br.com.domain.entities.User;

public record UserInLogin(String email, String password) {

	public User toDomainLogin(UserInLogin requestbody){
		return new User(requestbody.email, requestbody.password);
	}
}
