package dev.ssen.password.validator.predicates;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_LOWERCASE_MESSAGE;

import java.util.function.IntPredicate;

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
