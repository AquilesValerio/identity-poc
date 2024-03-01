package br.com.infrastructure.persistence;

import br.com.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByName(String userName);
	User findByEmail(String username);
}
