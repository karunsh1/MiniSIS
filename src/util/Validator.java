package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private Pattern patternPassword,patternEmail;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!$%]).{6,8})";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public Validator() {
		patternPassword = Pattern.compile(PASSWORD_PATTERN);
		patternEmail = Pattern.compile(EMAIL_PATTERN);
		
	}

	public boolean validate(final String password) {

		matcher = patternPassword.matcher(password);
		return matcher.matches();

	}
	public boolean validateEmail(final String email) {

		matcher = patternEmail.matcher(email);
		return matcher.matches();

	}

}
