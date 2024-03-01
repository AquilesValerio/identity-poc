package br.com.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordValidator {

	/*Esse validator valida os seguintes critérios abaixo:
	Deve ter pelo menos 8 caracteres e no máximo 20 caracteres.
	Deve conter pelo menos uma letra maiúscula.
	Deve conter pelo menos uma letra minúscula.
	Deve conter pelo menos um número.
	Deve conter pelo menos um caractere especial*/

	private static final String PASSWORD_REGEX =
		"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*])[a-zA-Z0-9@#$%^&*]{8,20}$";

	public static boolean isValid(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
