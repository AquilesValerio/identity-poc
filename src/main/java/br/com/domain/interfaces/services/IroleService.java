package br.com.domain.interfaces.services;

import br.com.domain.entities.Role;
import java.util.List;
import java.util.Optional;

public interface IroleService {
	Role insert(Role domain);
	Optional<Role> findById(int id);
	void deleteById(int id);
	List<Role> findAll();
	void update(Role role);
}
