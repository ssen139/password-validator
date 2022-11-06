package dev.ssen.password.validator.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ssen
 *
 */
public class PasswordValidator {
	private List<String> messages;

	public PasswordValidator() {
		messages = new ArrayList<String>();
	}

	public boolean validate(String password) {
		boolean isValid = true;
		if(password == null) {
			isValid = false;
			messages.add("Password should not be Empty");
			return isValid;
		}
		if(password.isEmpty()) {
			isValid = false;
			messages.add("Password should not be Empty");
		}
		if (password.length() < 8) {
			isValid = false;
			messages.add("Password length should be more than 8 chars");
		}

		return isValid;
	}

	public List<String> getMessages() {
		return messages;
	}
}
