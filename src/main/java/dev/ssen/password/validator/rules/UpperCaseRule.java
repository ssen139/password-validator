package dev.ssen.password.validator.rules;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_UPPERCASE_MESSAGE;
import java.util.Optional;

public class UpperCaseRule implements Rule {

	@Override
	public Optional<String> validate(String password) {
		boolean hasUpperCaseChar = password.chars()
		.anyMatch(ch -> Character.isLetter(ch) && Character.isUpperCase(ch));
		
		if(!hasUpperCaseChar)
			return Optional.of(PASSWORD_UPPERCASE_MESSAGE);
		
		return Optional.empty();
	}

}
