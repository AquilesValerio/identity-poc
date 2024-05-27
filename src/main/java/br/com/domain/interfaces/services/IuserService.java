package br.com.domain.interfaces.services;

import br.com.domain.entities.Role;
import br.com.domain.entities.User;
import br.com.infrastructure.DTO.IN.UserInLogin;
import br.com.infrastructure.DTO.IN.UserInPatch;
import br.com.infrastructure.DTO.IN.UserInUpdate;
import br.com.infrastructure.DTO.OUT.LoginOut;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IuserService {
	Long save(User user);
	Optional<User> findById(Long id);
	List<User> findAll();
	void delete(Long id);
	void update(Long id, UserInUpdate requestBody);
	void patchPassword(UserInPatch requestBody);
	LoginOut login(UserInLogin requestBody);
}
