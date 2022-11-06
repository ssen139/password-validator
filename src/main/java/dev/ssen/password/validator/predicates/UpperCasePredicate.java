package dev.ssen.password.validator.predicates;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_UPPERCASE_MESSAGE;

import java.util.function.IntPredicate;

public class UpperCasePredicate implements CharacterPredicate {

	@Override
	public IntPredicate getPredicate() {
		return (ch -> Character.isLetter(ch) && Character.isUpperCase(ch));
	}

	@Override
	public String getMismatchMessage() {
		return PASSWORD_UPPERCASE_MESSAGE;
	}


}
