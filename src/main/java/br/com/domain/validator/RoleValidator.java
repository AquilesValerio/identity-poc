package br.com.domain.validator;

import br.com.domain._share.NotificationPattern;
import br.com.domain.entities.Role;

public class RoleValidator {

	public static void validate(Role role, NotificationPattern notificationPattern) {
		if (role.getName() == null || role.getName().trim().isEmpty() || role.getName().length() > 50) {
			notificationPattern.addErro("This field is mandatory and must be 50 characters long.");
		}
	}
}
