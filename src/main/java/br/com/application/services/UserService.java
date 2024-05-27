package br.com.application.services;

import br.com.domain._share.ApiException;
import br.com.domain._share.NotificationPattern;
import br.com.domain.entities.User;
import br.com.domain.interfaces.gateway.IuserGateway;
import br.com.domain.interfaces.services.IuserService;
import br.com.infrastructure.DTO.IN.UserInLogin;
import br.com.infrastructure.DTO.IN.UserInPatch;
import br.com.infrastructure.DTO.IN.UserInUpdate;
import br.com.infrastructure.DTO.OUT.LoginOut;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IuserService {

	private final IuserGateway iuserGateway;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserService(IuserGateway iuserGateway, BCryptPasswordEncoder passwordEncoder) {

		this.iuserGateway = iuserGateway;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Long save(User user) {
		validate(user);
		existsByName(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		var result = iuserGateway.save(user);
		return result;
	}

	@Override
	public Optional<User> findById(Long id) {
		var result = iuserGateway.findById(id);
		return result;
	}

	@Override
	public List<User> findAll() {
		var result = iuserGateway.findAll();
		return result;
	}

	@Override
	public void delete(Long id) {
		iuserGateway.delete(id);
	}

	@Override
	public void update(Long id, UserInUpdate requestBody) {
		var entity = updateUser(id, requestBody);
		iuserGateway.update(entity);
	}

	@Override
	public void patchPassword(UserInPatch requestBody) {
		var entity = validatePassword(requestBody);
		iuserGateway.patchPassword(entity);
	}

	@Override
	public LoginOut login(UserInLogin requestBody) {
		var result = validatePasswordLogin(requestBody);
		return new LoginOut("sucesso", 10);
	}

	private User updateUser(Long id, UserInUpdate requestBody) {
		var entity = validateId(id);
		var entityUpdated = requestBody.toDomainUpdated(requestBody);
		entityUpdated.setId(id);
		entityUpdated.setPassword(entity.getPassword());
		return entityUpdated;
	}

	private void existsByName(User user) {
		var validate = iuserGateway.existsByName(user.getName());
		if (validate) {
			throw new ApiException(HttpStatus.CONFLICT, "This user already exists. ");
		}
	}

	private void validate(User user) {
		var erros = new NotificationPattern();
		user.validate(erros);
		if (erros.hasErros()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, erros.getListErros());
		}
	}

	private User validateEmail(String email) {
		var emailIsPresent = iuserGateway.findByEmail(email);
		if (emailIsPresent == null) {
			new ApiException(HttpStatus.BAD_REQUEST, "Register not found");
		}
		return emailIsPresent;
	}

	private User validateId(Long id) {
		return iuserGateway.findById(id)
			.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Register not found"));
	}

	private User validatePassword(UserInPatch requestBody) {
		var entity = validateEmail(requestBody.email());

		if (!isPasswordValid(requestBody.oldPassword(), entity.getPassword())) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Senha inválida");
		} else {
			entity.setPassword(passwordEncoder.encode(requestBody.newPassword()));
		}
		return entity;
	}

	private User validatePasswordLogin(UserInLogin requestBody) {
		var entity = validateEmail(requestBody.email());

		if (!isPasswordValid(requestBody.password(), entity.getPassword())) {
			throw new ApiException(HttpStatus.UNAUTHORIZED, "Login inválido");
		}
		return entity;
	}

	private boolean isPasswordValid(String oldPassword, String newPwassword){
		return passwordEncoder.matches(oldPassword, newPwassword);
	}
}


