package br.com.infrastructure.persistence;

import br.com.domain.entities.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Boolean existsByName(String roleName);
}
