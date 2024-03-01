package br.com.domain.validator;

import br.com.domain._share.NotificationPattern;
import br.com.domain.entities.User;
import br.com.domain.util.CpfValidator;
import br.com.domain.util.EmailValidator;
import br.com.domain.util.PasswordValidator;

public  class UserValidator {

	public static void validate(User user, NotificationPattern notificationPattern){
		if(user.getName() == null || user.getName().trim().isEmpty() || user.getName().length() > 50){
			notificationPattern.addErro("This field is mandatory and must be 50 characters long.");
		}
		if(!EmailValidator.isValid(user.getEmail())){
			notificationPattern.addErro("Must to be a valid email");
		}
		if(!PasswordValidator.isValid(user.getPassword())){
			notificationPattern.addErro("Must to be a valid password");
		}
		if(!CpfValidator.isValidCPF(user.getCpf())){
			notificationPattern.addErro("Must to be a valid cpf");
		}

	}
}
