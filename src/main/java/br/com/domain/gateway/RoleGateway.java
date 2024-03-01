package br.com.domain.gateway;

import br.com.domain.entities.Role;
import br.com.domain.interfaces.gateway.IroleGateway;
import br.com.infrastructure.persistence.RoleRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RoleGateway implements IroleGateway {

	private final RoleRepository roleRepository;

	public RoleGateway(RoleRepository roleRepository) {this.roleRepository = roleRepository;}

	@Override
	public Role insert(Role role) {
		existsByName(role.getName());
		var entity = roleRepository.save(role);
		return entity;
	}

	@Override
	public Boolean existsByName(String name) {
		 return roleRepository.existsByName(name);
	}

	@Override
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id).map(e-> Optional.ofNullable(e)).orElse(Optional.empty());
	}

	@Override
	public void deleteById(int id) {
		roleRepository.deleteById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Boolean existsById(Integer id) {
		return roleRepository.existsById(id);
	}

	@Override
	public void update(Role role) {
		roleRepository.save(role);
	}
}
