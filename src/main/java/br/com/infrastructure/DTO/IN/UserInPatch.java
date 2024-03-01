package br.com.infrastructure.DTO.IN;

import br.com.domain.entities.User;
import java.util.List;

public record UserInPatch(String email, String oldPassword, String newPassword) {


	public User toDomainPatch(Long id){
		return new User(null,null,null,null,null,null);
	}
}
