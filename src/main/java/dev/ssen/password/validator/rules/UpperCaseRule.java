package dev.ssen.password.validator.rules;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_UPPERCASE_MESSAGE;

import java.util.Optional;
import java.util.stream.IntStream;

public class UpperCaseRule implements Rule {

	@Override
	public Optional<String> validate(String password) {
		try (IntStream charStream = password.chars()) {
			boolean hasUpperCaseChar = charStream.anyMatch(ch -> Character.isLetter(ch) && Character.isUpperCase(ch));

			if (!hasUpperCaseChar)
				return Optional.of(PASSWORD_UPPERCASE_MESSAGE);
		} catch(NullPointerException e) {
			return Optional.empty();
		}

		return Optional.empty();
	}

}
