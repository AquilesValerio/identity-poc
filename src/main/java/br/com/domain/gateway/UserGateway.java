package br.com.domain.gateway;

import br.com.domain.entities.User;
import br.com.domain.interfaces.gateway.IuserGateway;
import br.com.infrastructure.persistence.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserGateway implements IuserGateway {
	private final UserRepository userRepository;

	public UserGateway(UserRepository userRepository) {this.userRepository = userRepository;}

	@Override
	public Long save(User user) {
		var result = userRepository.save(user);
		return result.getId();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id).map(e -> Optional.ofNullable(e)).orElse(Optional.empty());
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Boolean existsByName(String name) {
		return userRepository.existsByName(name);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public void update(User requestBody) {
		userRepository.save(requestBody);
	}

	@Override
	public User patchPassword(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
