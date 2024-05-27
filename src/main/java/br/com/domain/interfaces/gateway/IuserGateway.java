package br.com.domain.interfaces.gateway;

import br.com.domain.entities.Role;
import br.com.domain.entities.User;
import java.util.List;
import java.util.Optional;

public interface IuserGateway {
	Long save(User user);
	Optional<User> findById(Long id);
	List<User> findAll();
	Boolean existsByName(String name);
	void delete(Long id);
	Boolean existsById(Long id);
	void update(User user);
	User patchPassword (User user);
	User findByEmail(String username);

}
