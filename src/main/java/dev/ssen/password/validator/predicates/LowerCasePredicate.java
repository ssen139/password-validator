package dev.ssen.password.validator.predicates;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_LOWERCASE_MESSAGE;

import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import dev.ssen.password.validator.rules.Rule;

public class LowerCasePredicate implements CharacterPredicate {

	@Override
	public IntPredicate getPredicate() {
		return (ch -> Character.isLetter(ch) && Character.isLowerCase(ch));
	}

	@Override
	public String getMismatchMessage() {
		return PASSWORD_LOWERCASE_MESSAGE;
	}


}
