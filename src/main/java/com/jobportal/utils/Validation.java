package com.jobportal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	// digit + lowercase char + uppercase char + punctuation + symbol=no white space
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$";

	private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

	public static boolean isValidPassword(final String password) {
		Matcher matcher = PATTERN.matcher(password);
		return matcher.matches();
	}
}
