package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!$%]).{6,8})";
	

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	public boolean validate(final String password) {

		matcher = pattern.matcher(password);
		return matcher.matches();

	}

}
