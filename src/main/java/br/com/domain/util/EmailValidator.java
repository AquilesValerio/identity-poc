package br.com.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailValidator {

	private static final String EMAIL_REGEX =
		"^[\\w-]+@[\\w-]+\\.[\\w-]+(\\.[\\w-]+)*$";

	public static boolean isValid(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
