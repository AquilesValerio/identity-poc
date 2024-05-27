package br.com.infrastructure.DTO.OUT;

import br.com.domain.entities.Role;
import br.com.domain.entities.User;
import java.util.List;
import java.util.Optional;

public record UserOut(Long id, String name, String email, String password, String cpf, List<Role> roleList) {

	public static UserOut toOut(User user){
		return new UserOut(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getEmail(),
			user.getRoleList());
	}

	public static Optional<UserOut> toOutOptional(Optional<User> userOptional){
		if(userOptional.isEmpty()){
			return Optional.empty();
		}
		var result = userOptional.get();

		return Optional.of(new UserOut(result.getId(), result.getName(), result.getEmail(), result.getPassword(),
			result.getCpf(), result.getRoleList()));
	}
}
