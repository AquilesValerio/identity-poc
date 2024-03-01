package br.com.domain.interfaces.gateway;

import br.com.domain.entities.Role;
import br.com.infrastructure.DTO.OUT.RoleOut;
import java.util.List;
import java.util.Optional;

public interface IroleGateway {
	Role insert(Role role);
	Boolean existsByName(String name);
	Optional<Role> findById(int id);
	void deleteById (int id);
	List<Role> findAll();
	Boolean existsById(Integer id);
	void update(Role role);
}
