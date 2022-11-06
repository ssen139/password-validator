package dev.ssen.password.validator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.ssen.password.validator.rules.LengthRule;
import dev.ssen.password.validator.rules.UpperCaseRule;

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

		Optional<String> lengthRuleMessage = new LengthRule().validate(password);
		if(lengthRuleMessage.isPresent()) {
			messages.add(lengthRuleMessage.get());
		}else {
			Optional<String> upperCaseRuleMessage = new UpperCaseRule().validate(password);
			upperCaseRuleMessage.ifPresent(msg -> messages.add(upperCaseRuleMessage.get()));
		}
		
		isValid = messages.isEmpty();
		return isValid;
	}

	public List<String> getMessages() {
		return messages;
	}
}
