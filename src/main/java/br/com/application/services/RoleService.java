package br.com.application.services;

import br.com.domain._share.ApiException;
import br.com.domain._share.NotificationPattern;
import br.com.domain.entities.Role;
import br.com.domain.interfaces.gateway.IroleGateway;
import br.com.domain.interfaces.services.IroleService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IroleService {
	private final IroleGateway iroleGateway;

	public RoleService(IroleGateway iroleGateway) {this.iroleGateway = iroleGateway;}

	@Override
	public Role insert(Role roleRequest) {
		validateRole(roleRequest);
		existsByName(roleRequest);
		return iroleGateway.insert(roleRequest);
	}

	@Override
	public Optional<Role> findById(int id) {
		return iroleGateway.findById(id);
	}

	@Override
	public void deleteById(int id) {
		iroleGateway.deleteById(id);
	}

	@Override
	public List<Role> findAll() {
		return iroleGateway.findAll();
	}

	@Override
	public void update(Role role) {
		validateId(role.getId());
		iroleGateway.update(role);
	}

	private void validateId(int id) {
		var exist = iroleGateway.existsById(id);
		if (!exist) {
			throw new ApiException(HttpStatus.NOT_FOUND, "Register not found");
		}
	}

	private void existsByName(Role role) {
		var validate = iroleGateway.existsByName(role.getName());

		if (validate) {
			throw new ApiException(HttpStatus.CONFLICT, "This role already exists. ");
		}
	}

	private void validateRole(Role role) {
		NotificationPattern notificationPattern = new NotificationPattern();
		role.validate(notificationPattern);
		if (notificationPattern.hasErros()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, notificationPattern.getListErros());
		}
	}
}


